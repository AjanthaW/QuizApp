package com.ajantha.quiz.presentation.home.quiz.model

data class QuizQuestionModel(
    val quote: String,
    val question: String,
    val options: List<String>
)