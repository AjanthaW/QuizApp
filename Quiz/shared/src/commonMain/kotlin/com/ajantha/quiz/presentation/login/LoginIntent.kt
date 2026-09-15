package com.ajantha.quiz.presentation.login

sealed interface LoginIntent {
    data class EmailChanged(
        val value: String
    ) : LoginIntent

    data class PasswordChanged(
        val value: String
    ) : LoginIntent

    data object LoginClicked : LoginIntent
    data object Reset : LoginIntent
}