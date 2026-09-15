package com.ajantha.quizserver.dto

data class UserResponse(
    val id: Long,
    val email: String,
    val name: String,
    val profileUrl: String?,
    val phone: String?,
    val country: String?,
    val bio: String?
)