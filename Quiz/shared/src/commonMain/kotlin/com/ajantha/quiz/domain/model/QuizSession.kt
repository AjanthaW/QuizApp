package com.ajantha.quiz.domain.model

data class QuizSession(
    val id: String,
    val mode: QuizMode,
    val questions: List<QuizQuestion>
)