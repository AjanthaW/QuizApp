package com.ajantha.quiz.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Int,
    val email: String,
    val name: String,
    val profileUrl: String,
    val phone: String,
    val country: String,
    val bio: String
)
