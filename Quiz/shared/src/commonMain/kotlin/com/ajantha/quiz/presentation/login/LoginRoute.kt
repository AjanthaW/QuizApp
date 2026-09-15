package com.ajantha.quiz.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginRoute(
    onLoginSuccess: () -> Unit
) {
    val viewModel = koinViewModel<LoginViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onIntent(LoginIntent.Reset)
    }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                LoginEffect.LoginSuccess -> {
                    onLoginSuccess()
                }
            }
        }
    }

    LoginScreen(
        state = state,
        onIntent = viewModel::onIntent
    )
}