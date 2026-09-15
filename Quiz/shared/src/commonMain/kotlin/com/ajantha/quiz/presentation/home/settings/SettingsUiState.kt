package com.ajantha.quiz.presentation.home.settings

import com.ajantha.quiz.domain.model.QuizMode

data class SettingsUiState(
    val mode: QuizMode = QuizMode.BINARY
)