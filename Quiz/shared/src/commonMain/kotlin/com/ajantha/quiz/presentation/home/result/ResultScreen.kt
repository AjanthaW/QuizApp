package com.ajantha.quiz.presentation.home.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import quiz.shared.generated.resources.Res
import quiz.shared.generated.resources.quiz_complete
import quiz.shared.generated.resources.result_answered
import quiz.shared.generated.resources.result_correct_answers
import quiz.shared.generated.resources.result_wrong_answers
import quiz.shared.generated.resources.start_again

@Composable
fun ResultScreen(
    correctAnswers: Int,
    totalQuestions: Int,
    onStartAgain: () -> Unit
) {
    val wrongAnswers = totalQuestions - correctAnswers

    val percentage = if (totalQuestions > 0) {
        correctAnswers * 100 / totalQuestions
    } else {
        0
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(Res.string.quiz_complete),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "$percentage%",
                    style = MaterialTheme.typography.displaySmall
                )

                Text(
                    text = stringResource(
                        Res.string.result_answered,
                        correctAnswers,
                        totalQuestions
                    ),
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = stringResource(
                        Res.string.result_correct_answers,
                        correctAnswers
                    )
                )

                Text(
                    text = stringResource(
                        Res.string.result_wrong_answers,
                        wrongAnswers
                    )
                )
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onStartAgain
        ) {
            Text(stringResource(Res.string.start_again))
        }
    }
}