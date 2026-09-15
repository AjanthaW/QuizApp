package com.ajantha.quiz.presentation.home

import com.ajantha.quiz.presentation.home.navigation.MainTab

data class HomeUiState(
    val currentTab: MainTab = MainTab.QUIZ
)