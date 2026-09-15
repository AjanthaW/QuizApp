package com.ajantha.quiz.presentation.home

import com.ajantha.quiz.presentation.home.navigation.MainTab

sealed interface HomeIntent {
    data class SelectTab(
        val tab: MainTab
    ) : HomeIntent
}