package com.ajantha.quiz.presentation.home.quiz

import androidx.compose.runtime.Composable
import com.ajantha.quiz.presentation.home.quiz.component.QuizContent
import com.ajantha.quiz.presentation.home.quiz.model.QuizUiState

@Composable
fun QuizScreen(
    state: QuizUiState,
    onIntent: (QuizIntent) -> Unit
) {
    QuizContent(
        state = state,
        onIntent = onIntent
    )
}