package com.ajantha.quiz.presentation.home.quiz.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ajantha.quiz.presentation.home.quiz.QuizIntent
import com.ajantha.quiz.presentation.home.quiz.model.QuizUiState

@Composable
fun QuizContent(
    state: QuizUiState,
    onIntent: (QuizIntent) -> Unit
) {
    when {
        state.isLoading && state.question == null -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.question != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                QuizQuestionContent(
                    state = state,
                    onIntent = onIntent
                )
            }
        }

        state.errorMessage != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = state.errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }

    if (state.showAnswerDialog) {
        QuizAnswerDialog(
            correct = state.answerCorrect,
            correctAnswer = state.correctAnswer,
            onContinue = {
                onIntent(
                    QuizIntent.Continue
                )
            }
        )
    }
}