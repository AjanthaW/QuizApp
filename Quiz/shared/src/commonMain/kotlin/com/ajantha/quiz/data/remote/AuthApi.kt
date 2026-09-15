package com.ajantha.quiz.data.remote

import com.ajantha.quiz.data.remote.model.LoginResponse

interface AuthApi {

    suspend fun login(
        email: String,
        password: String
    ): LoginResponse

}