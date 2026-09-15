package com.ajantha.quiz.presentation.home.profile.model

import com.ajantha.quiz.core.session.User

data class ProfileUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val errorMessage: String? = null
)