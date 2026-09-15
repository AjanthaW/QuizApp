package com.ajantha.quiz.presentation.home.quiz.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ajantha.quiz.presentation.home.quiz.QuizIntent
import com.ajantha.quiz.presentation.home.quiz.model.QuizUiState
import org.jetbrains.compose.resources.stringResource
import quiz.shared.generated.resources.Res
import quiz.shared.generated.resources.quiz

@Composable
fun QuizQuestionContent(
    state: QuizUiState,
    onIntent: (QuizIntent) -> Unit
) {
    val question = state.question ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(Res.string.quiz),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Question ${state.questionNumber} of ${state.totalQuestions}",
            style = MaterialTheme.typography.titleMedium
        )

        LinearProgressIndicator(
            progress = { state.progress },
            modifier = Modifier.fillMaxWidth()
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = question.quote,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = question.question,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            question.options.forEach { option ->
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !state.isSubmitting,
                    onClick = {
                        onIntent(
                            QuizIntent.SelectAnswer(option)
                        )
                    }
                ) {
                    Text(option)
                }
            }
        }

        if (state.isSubmitting) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}