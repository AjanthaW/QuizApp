package com.ajantha.quiz.presentation.home.quiz.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ajantha.quiz.core.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import quiz.shared.generated.resources.Res
import quiz.shared.generated.resources.ok
import quiz.shared.generated.resources.quiz_correct_message
import quiz.shared.generated.resources.quiz_correct_title
import quiz.shared.generated.resources.quiz_wrong_message
import quiz.shared.generated.resources.quiz_wrong_title

@Composable
fun QuizAnswerDialog(
    correct: Boolean,
    correctAnswer: String,
    onContinue: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                text = stringResource(
                    if (correct) {
                        Res.string.quiz_correct_title
                    } else {
                        Res.string.quiz_wrong_title
                    }
                ), color = if (correct) {
                    AppTheme.colors.success
                } else {
                    AppTheme.colors.error
                }
            )
        },
        text = {
            Text(
                text = stringResource(
                    if (correct) {
                        Res.string.quiz_correct_message
                    } else {
                        Res.string.quiz_wrong_message
                    },
                    correctAnswer
                )
            )
        },
        confirmButton = {
            Button(
                onClick = onContinue
            ) {
                Text(
                    text = stringResource(Res.string.ok)
                )
            }
        }
    )
}