package com.ajantha.quiz.core.session

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val email: String,
    val name: String,
    val profileUrl: String,
    val phone: String,
    val country: String,
    val bio: String
)