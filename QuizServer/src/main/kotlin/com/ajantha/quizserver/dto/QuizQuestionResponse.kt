package com.ajantha.quizserver.dto

data class QuizQuestionResponse(
    val id: Long,
    val quote: String,
    val question: String,
    val options: List<String>
)