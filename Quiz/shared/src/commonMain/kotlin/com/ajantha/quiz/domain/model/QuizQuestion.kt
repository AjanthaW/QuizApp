package com.ajantha.quiz.domain.model

data class QuizQuestion(
    val id: Long,
    val quote: String,
    val question: String,
    val options: List<String>
)