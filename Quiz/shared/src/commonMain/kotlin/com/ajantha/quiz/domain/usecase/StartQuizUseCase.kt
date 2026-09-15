package com.ajantha.quiz.domain.usecase

import com.ajantha.quiz.domain.model.QuizMode
import com.ajantha.quiz.domain.model.QuizSession
import com.ajantha.quiz.domain.repository.QuizRepository

class StartQuizUseCase(
    private val repository: QuizRepository
) {

    suspend operator fun invoke(
        mode: QuizMode
    ): QuizSession {
        return repository.createSession(mode)
    }

}