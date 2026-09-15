package com.ajantha.quiz.core.session

interface SessionStorage {

    suspend fun saveSession(session: UserSession)

    suspend fun getSession(): UserSession?

    suspend fun getToken(): String?

    suspend fun clearSession()
}