package com.ajantha.quiz.core.session

import kotlinx.serialization.Serializable

@Serializable
data class UserSession(
    val token: String,
    val user: User
)