package com.ajantha.quiz.presentation.home.quiz

sealed interface QuizEffect {
    data class NavigateToResult(
        val correctAnswers: Int,
        val totalQuestions: Int
    ) : QuizEffect
}