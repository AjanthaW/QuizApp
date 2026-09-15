package com.ajantha.quiz.core.mapper

import com.ajantha.quiz.data.remote.model.AnswerResponse
import com.ajantha.quiz.data.remote.model.QuizQuestionResponse
import com.ajantha.quiz.data.remote.model.QuizSessionResponse
import com.ajantha.quiz.domain.model.Answer
import com.ajantha.quiz.domain.model.QuizMode
import com.ajantha.quiz.domain.model.QuizQuestion
import com.ajantha.quiz.domain.model.QuizSession
import com.ajantha.quiz.presentation.home.quiz.model.QuizQuestionModel

fun QuizSessionResponse.toDomain(): QuizSession {
    return QuizSession(
        id = sessionId,
        mode = QuizMode.valueOf(mode),
        questions = questions.map { it.toDomain() }
    )
}

fun QuizQuestionResponse.toDomain(): QuizQuestion {
    return QuizQuestion(
        id = id,
        quote = quote,
        question = question,
        options = options
    )
}

fun AnswerResponse.toDomain(): Answer {
    return Answer(
        correct = correct,
        correctAnswer = correctAnswer,
        currentQuestion = currentQuestion,
        totalQuestions = totalQuestions,
        correctAnswers = correctAnswers,
        completed = completed
    )
}

fun QuizQuestion.toModel(): QuizQuestionModel {
    return QuizQuestionModel(
        quote = quote,
        question = question,
        options = options
    )
}