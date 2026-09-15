package com.ajantha.quizserver.service

import com.ajantha.quizserver.dto.AnswerRequest
import com.ajantha.quizserver.dto.AnswerResponse
import com.ajantha.quizserver.dto.QuizQuestionResponse
import com.ajantha.quizserver.dto.QuizSessionResponse
import com.ajantha.quizserver.quiz.QuizMode
import com.ajantha.quizserver.quiz.QuizQuestion
import com.ajantha.quizserver.quiz.QuizSession
import com.ajantha.quizserver.quiz.QuizSessionStore
import com.ajantha.quizserver.repository.QuoteRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.random.Random

@Service
class QuizService(
    private val quoteRepository: QuoteRepository,
    private val quizSessionStore: QuizSessionStore
) {

    fun createSession(
        email: String,
        mode: QuizMode
    ): QuizSessionResponse {

        val quotes = quoteRepository.findAll()
            .shuffled()
            .take(10)

        require(quotes.size >= 10) {
            "At least 10 quotes are required"
        }

        val questions = quotes.map { quote ->
            when (mode) {
                QuizMode.BINARY -> {
                    val useCorrectAuthor = Random.nextBoolean()
                    val displayedAuthor =
                        if (useCorrectAuthor) {
                            quote.author
                        } else {
                            quoteRepository
                                .findAll()
                                .map { it.author }
                                .filter { it != quote.author }
                                .random()
                        }

                    QuizQuestion(
                        id = quote.id,
                        quote = quote.quote,
                        question = "Did $displayedAuthor say this quote?",
                        options = listOf(
                            "Yes",
                            "No"
                        ),
                        correctAnswer = if (useCorrectAuthor) {
                            "Yes"
                        } else {
                            "No"
                        }
                    )
                }

                QuizMode.MULTIPLE_CHOICE -> {
                    val distractors = quoteRepository
                        .findAll()
                        .map { it.author }
                        .filter { it != quote.author }
                        .distinct()
                        .shuffled()
                        .take(2)

                    QuizQuestion(
                        id = quote.id,
                        quote = quote.quote,
                        question = "Who said this quote?",
                        options = (distractors + quote.author).shuffled(),
                        correctAnswer = quote.author
                    )
                }
            }
        }

        val session = QuizSession(
            id = UUID.randomUUID().toString(),
            userEmail = email,
            mode = mode,
            questions = questions
        )

        quizSessionStore.save(session)

        return QuizSessionResponse(
            sessionId = session.id,
            mode = session.mode.name,
            questions = session.questions.map {
                QuizQuestionResponse(
                    id = it.id,
                    quote = it.quote,
                    question = it.question,
                    options = it.options
                )
            }
        )
    }

    fun submitAnswer(
        email: String,
        sessionId: String,
        request: AnswerRequest
    ): AnswerResponse {

        val session = quizSessionStore.get(sessionId) ?: throw IllegalArgumentException("Quiz session not found")

        require(session.userEmail == email) {
            "Quiz session does not belong to this user"
        }

        require(session.currentQuestionIndex < session.questions.size) {
            "Quiz session has already completed"
        }

        val question = session.questions[session.currentQuestionIndex]

        require(question.id == request.questionId) {
            "Invalid question"
        }

        val correct = request.answer == question.correctAnswer

        if (correct) {
            session.correctAnswers++
        }

        session.currentQuestionIndex++

        val completed = session.currentQuestionIndex >= session.questions.size

        val response = AnswerResponse(
            correct = correct,
            correctAnswer = question.correctAnswer,
            currentQuestion = session.currentQuestionIndex,
            totalQuestions = session.questions.size,
            correctAnswers = session.correctAnswers,
            completed = completed
        )

        if (completed) {
            quizSessionStore.remove(sessionId)
        }

        return response
    }
}