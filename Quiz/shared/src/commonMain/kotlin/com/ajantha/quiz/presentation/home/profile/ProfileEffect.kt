package com.ajantha.quiz.presentation.home.profile


sealed interface ProfileEffect {
    data object NavigateToLogin : ProfileEffect
}