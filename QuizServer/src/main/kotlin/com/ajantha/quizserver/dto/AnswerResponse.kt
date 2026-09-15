package com.ajantha.quizserver.dto

data class AnswerResponse(
    val correct: Boolean,
    val correctAnswer: String,
    val currentQuestion: Int,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val completed: Boolean
)