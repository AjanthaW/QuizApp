package com.ajantha.quiz.presentation.home.settings

import com.ajantha.quiz.domain.model.QuizMode

sealed interface SettingsIntent {
    data class SetMode(
        val mode: QuizMode
    ) : SettingsIntent
}