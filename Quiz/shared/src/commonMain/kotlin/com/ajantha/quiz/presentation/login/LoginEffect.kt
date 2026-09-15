package com.ajantha.quiz.presentation.login

sealed interface LoginEffect {
    data object LoginSuccess : LoginEffect
}