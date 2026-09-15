package com.ajantha.quiz.presentation.splash

sealed interface SplashEffect {
    data class SessionChecked(
        val isLoggedIn: Boolean
    ) : SplashEffect
}