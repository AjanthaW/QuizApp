package com.ajantha.quiz.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashRoute(
    onSessionChecked: (Boolean) -> Unit
) {
    val viewModel = koinViewModel<SplashViewModel>()

    var splashFinished by remember {
        mutableStateOf(false)
    }

    var isLoggedIn by remember {
        mutableStateOf<Boolean?>(null)
    }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SplashEffect.SessionChecked -> {
                    isLoggedIn = effect.isLoggedIn
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onIntent(SplashIntent.CheckSession)
    }

    LaunchedEffect(
        splashFinished,
        isLoggedIn
    ) {
        if (splashFinished && isLoggedIn != null) {
            onSessionChecked(isLoggedIn!!)
        }
    }

    SplashScreen(
        onFinished = {
            splashFinished = true
        }
    )
}