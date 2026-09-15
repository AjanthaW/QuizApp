package com.ajantha.quiz.presentation.home.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ajantha.quiz.domain.model.QuizMode
import org.jetbrains.compose.resources.stringResource
import quiz.shared.generated.resources.Res
import quiz.shared.generated.resources.multiple_choice
import quiz.shared.generated.resources.quiz_mode
import quiz.shared.generated.resources.settings
import quiz.shared.generated.resources.yes_no

@Composable
fun SettingsScreen(
    state: SettingsUiState,
    onIntent: (SettingsIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(Res.string.settings),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(Res.string.quiz_mode),
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = when (state.mode) {
                            QuizMode.BINARY -> stringResource(Res.string.yes_no)
                            QuizMode.MULTIPLE_CHOICE -> stringResource(Res.string.multiple_choice)
                        },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Switch(
                    checked = state.mode == QuizMode.MULTIPLE_CHOICE,
                    onCheckedChange = { checked ->
                        onIntent(
                            SettingsIntent.SetMode(
                                if (checked) {
                                    QuizMode.MULTIPLE_CHOICE
                                } else {
                                    QuizMode.BINARY
                                }
                            )
                        )
                    }
                )
            }
        }
    }
}