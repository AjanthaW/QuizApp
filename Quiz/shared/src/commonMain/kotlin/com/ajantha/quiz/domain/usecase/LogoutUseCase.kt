package com.ajantha.quiz.domain.usecase

import com.ajantha.quiz.domain.repository.AuthRepository

class LogoutUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() {
        repository.logout()
    }

}