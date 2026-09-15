package com.ajantha.quiz.presentation.home.quiz

import com.ajantha.quiz.domain.model.QuizMode

sealed interface QuizIntent {
    data object Start : QuizIntent
    data class SelectAnswer(
        val answer: String
    ) : QuizIntent

    data object Continue : QuizIntent
    data object StartAgain : QuizIntent
    data class ChangeMode(
        val mode: QuizMode
    ) : QuizIntent

    data object ClearError : QuizIntent
}