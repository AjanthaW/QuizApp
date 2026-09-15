package com.ajantha.quiz

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ajantha.quiz.core.navigation.AppNavigation
import com.ajantha.quiz.core.theme.QuizTheme

@Composable
@Preview
fun App() {
    QuizTheme {
        AppNavigation()
    }
}