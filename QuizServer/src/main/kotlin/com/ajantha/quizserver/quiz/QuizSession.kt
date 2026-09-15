package com.ajantha.quizserver.quiz

import java.util.*

data class QuizSession(
    val id: String = UUID.randomUUID().toString(),
    val userEmail: String,
    val mode: QuizMode,
    val questions: List<QuizQuestion>,
    var currentQuestionIndex: Int = 0,
    var correctAnswers: Int = 0
)