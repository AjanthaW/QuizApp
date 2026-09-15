package com.ajantha.quizserver.quiz

data class QuizQuestion(
    val id: Long,
    val quote: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)