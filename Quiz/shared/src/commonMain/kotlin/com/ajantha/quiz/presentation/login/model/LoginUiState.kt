package com.ajantha.quiz.presentation.login.model

import org.jetbrains.compose.resources.StringResource

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: StringResource? = null,
    val passwordError: StringResource? = null,
    val isLoading: Boolean = false,
    val loginError: String? = null
)