package com.ajantha.quiz.presentation.splash

sealed interface SplashIntent {
    data object CheckSession : SplashIntent
}