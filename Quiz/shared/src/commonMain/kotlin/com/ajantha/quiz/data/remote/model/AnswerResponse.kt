package com.ajantha.quiz.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AnswerResponse(
    val correct: Boolean,
    val correctAnswer: String,
    val currentQuestion: Int,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val completed: Boolean
)