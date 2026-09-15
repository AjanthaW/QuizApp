package com.ajantha.quiz

import androidx.compose.ui.window.ComposeUIViewController
import com.ajantha.quiz.di.appModules
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            appModules
        )
    }
}

fun MainViewController() = ComposeUIViewController { App() }