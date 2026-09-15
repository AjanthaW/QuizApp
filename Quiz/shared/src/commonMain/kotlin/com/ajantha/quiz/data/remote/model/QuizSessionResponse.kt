package com.ajantha.quiz.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class QuizSessionResponse(
    val sessionId: String,
    val mode: String,
    val questions: List<QuizQuestionResponse>
)