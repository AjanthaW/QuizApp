package com.ajantha.quiz.domain.repository

import com.ajantha.quiz.core.session.UserSession

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): UserSession

    suspend fun getSession(): UserSession?

    suspend fun logout()

}