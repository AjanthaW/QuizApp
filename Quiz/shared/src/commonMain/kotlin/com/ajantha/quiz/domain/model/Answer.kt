package com.ajantha.quiz.domain.model

data class Answer(
    val correct: Boolean,
    val correctAnswer: String,
    val currentQuestion: Int,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val completed: Boolean
)