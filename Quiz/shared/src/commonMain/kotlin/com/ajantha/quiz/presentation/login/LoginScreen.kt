package com.ajantha.quiz.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ajantha.quiz.core.theme.AppTheme
import com.ajantha.quiz.presentation.login.component.LoginTextField
import com.ajantha.quiz.presentation.login.model.LoginUiState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import quiz.shared.generated.resources.Res
import quiz.shared.generated.resources.app_logo
import quiz.shared.generated.resources.email
import quiz.shared.generated.resources.login
import quiz.shared.generated.resources.password
import quiz.shared.generated.resources.sign_in_to_continue
import quiz.shared.generated.resources.welcome

@Composable
fun LoginScreen(
    state: LoginUiState,
    onIntent: (LoginIntent) -> Unit
) {
    val colors = AppTheme.colors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(64.dp)
        )

        Image(
            painter = painterResource(Res.drawable.app_logo),
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(120.dp),
            contentDescription = "Famous Quote Quiz"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = stringResource(Res.string.welcome),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = stringResource(Res.string.sign_in_to_continue),
            style = MaterialTheme.typography.bodyMedium,
            color = colors.textSecondary
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LoginTextField(
            value = state.email,
            label = Res.string.email,
            error = state.emailError,
            keyboardType = KeyboardType.Email,
            enabled = !state.isLoading,
            onValueChange = {
                onIntent(
                    LoginIntent.EmailChanged(it)
                )
            }
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        LoginTextField(
            value = state.password,
            label = Res.string.password,
            error = state.passwordError,
            keyboardType = KeyboardType.Password,
            isPassword = true,
            enabled = !state.isLoading,
            onValueChange = {
                onIntent(
                    LoginIntent.PasswordChanged(it)
                )
            }
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        state.loginError?.let { error ->
            Text(
                text = error,
                color = colors.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

        Button(
            onClick = {
                onIntent(
                    LoginIntent.LoginClicked
                )
            },
            enabled = !state.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = colors.onPrimary,
                disabledContainerColor = colors.primary.copy(alpha = 0.6f),
                disabledContentColor = colors.onPrimary
            )
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(22.dp),
                    color = colors.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = stringResource(Res.string.login),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(
            modifier = Modifier.height(32.dp)
        )
    }
}

