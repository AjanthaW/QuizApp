package com.ajantha.quiz.presentation.home.profile

sealed interface ProfileIntent {
    data object Load : ProfileIntent
    data object Logout : ProfileIntent
}