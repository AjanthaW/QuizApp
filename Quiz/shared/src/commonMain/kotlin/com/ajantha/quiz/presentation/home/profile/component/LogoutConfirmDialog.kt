package com.ajantha.quiz.presentation.home.profile.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import quiz.shared.generated.resources.Res
import quiz.shared.generated.resources.cancel
import quiz.shared.generated.resources.logout
import quiz.shared.generated.resources.logout_message

@Composable
fun LogoutConfirmDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(Res.string.logout)
            )
        },
        text = {
            Text(
                text = stringResource(Res.string.logout_message)
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(
                    text = stringResource(Res.string.logout)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = stringResource(Res.string.cancel)
                )
            }
        }
    )
}