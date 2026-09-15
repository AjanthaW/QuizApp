package com.ajantha.quiz.presentation.home.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun QuizRoute(
    quizId: String,
    onOpenResult: (
        correctAnswers: Int,
        totalQuestions: Int
    ) -> Unit
) {
    val viewModel: QuizViewModel = koinViewModel(key = "quiz_$quizId")
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.onIntent(QuizIntent.Start)
    }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is QuizEffect.NavigateToResult -> {
                    onOpenResult(
                        effect.correctAnswers,
                        effect.totalQuestions
                    )
                }
            }
        }
    }

    QuizScreen(
        state = state,
        onIntent = viewModel::onIntent
    )
}