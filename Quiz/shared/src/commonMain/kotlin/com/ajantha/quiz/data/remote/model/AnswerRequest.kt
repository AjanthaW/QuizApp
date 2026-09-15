package com.ajantha.quiz.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AnswerRequest(
    val questionId: Long,
    val answer: String
)