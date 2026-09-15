package com.ajantha.quiz.data.repository

import com.ajantha.quiz.core.mapper.toDomain
import com.ajantha.quiz.core.session.SessionStorage
import com.ajantha.quiz.core.session.UserSession
import com.ajantha.quiz.data.remote.AuthApi
import com.ajantha.quiz.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val sessionStorage: SessionStorage
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): UserSession {
        val response = api.login(
            email = email,
            password = password
        )

        val session = UserSession(
            token = response.token,
            user = response.user.toDomain()
        )

        sessionStorage.saveSession(session)
        return session
    }

    override suspend fun getSession(): UserSession? {
        return sessionStorage.getSession()
    }

    override suspend fun logout() {
        sessionStorage.clearSession()
    }

}