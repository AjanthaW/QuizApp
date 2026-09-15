package com.ajantha.quiz.domain.usecase

import com.ajantha.quiz.core.session.User
import com.ajantha.quiz.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(): User? {
        return repository.getProfile()
    }

}