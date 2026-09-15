package com.ajantha.quiz.domain.usecase

import com.ajantha.quiz.core.session.UserSession
import com.ajantha.quiz.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): UserSession {
        return repository.login(
            email = email,
            password = password
        )
    }

}