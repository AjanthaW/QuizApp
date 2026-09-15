package com.ajantha.quiz.domain.repository

import com.ajantha.quiz.core.session.User

interface ProfileRepository {

    suspend fun getProfile(): User?

    suspend fun logout()

}