package com.ajantha.quizserver.dto

data class LoginResponse(
    val token: String,
    val user: UserResponse
)