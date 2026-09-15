package com.ajantha.quiz.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class QuizQuestionResponse(
    val id: Long,
    val quote: String,
    val question: String,
    val options: List<String>
)