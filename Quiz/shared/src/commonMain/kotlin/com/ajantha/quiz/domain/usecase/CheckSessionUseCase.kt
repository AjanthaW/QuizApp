package com.ajantha.quiz.domain.usecase

import com.ajantha.quiz.core.session.UserSession
import com.ajantha.quiz.domain.repository.AuthRepository

class CheckSessionUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): UserSession? {
        return repository.getSession()
    }

}