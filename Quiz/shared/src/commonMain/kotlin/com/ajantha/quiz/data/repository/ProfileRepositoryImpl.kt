package com.ajantha.quiz.data.repository

import com.ajantha.quiz.core.session.SessionStorage
import com.ajantha.quiz.core.session.User
import com.ajantha.quiz.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val sessionStorage: SessionStorage
) : ProfileRepository {

    override suspend fun getProfile(): User? {
        return sessionStorage.getSession()?.user
    }

    override suspend fun logout() {
        sessionStorage.clearSession()
    }

}