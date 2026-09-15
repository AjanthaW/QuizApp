package com.ajantha.quizserver.dto

data class QuizSessionResponse(
    val sessionId: String,
    val mode: String,
    val questions: List<QuizQuestionResponse>
)