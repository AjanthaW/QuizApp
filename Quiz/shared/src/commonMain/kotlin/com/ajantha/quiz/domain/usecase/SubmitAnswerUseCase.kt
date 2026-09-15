package com.ajantha.quiz.domain.usecase

import com.ajantha.quiz.domain.model.Answer
import com.ajantha.quiz.domain.repository.QuizRepository

class SubmitAnswerUseCase(
    private val repository: QuizRepository
) {

    suspend operator fun invoke(
        sessionId: String,
        questionId: Long,
        answer: String
    ): Answer {
        return repository.submitAnswer(
            sessionId = sessionId,
            questionId = questionId,
            answer = answer
        )
    }

}