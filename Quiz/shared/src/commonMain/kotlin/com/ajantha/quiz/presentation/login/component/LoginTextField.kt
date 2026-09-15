package com.ajantha.quiz.presentation.login.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ajantha.quiz.core.theme.AppTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginTextField(
    value: String,
    label: StringResource,
    error: StringResource?,
    keyboardType: KeyboardType,
    isPassword: Boolean = false,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit
) {
    val colors = AppTheme.colors

    var passwordVisible by rememberSaveable() {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            label = {
                Text(
                    text = stringResource(label)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(label)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = if (isPassword) {
                    ImeAction.Done
                } else {
                    ImeAction.Next
                }
            ),
            visualTransformation = if (
                isPassword && !passwordVisible
            ) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            leadingIcon = {
                Icon(
                    imageVector = if (
                        isPassword
                    ) {
                        Icons.Filled.Password
                    } else {
                        Icons.Filled.Email
                    },
                    contentDescription = null
                )
            },
            trailingIcon = if (isPassword) {
                {
                    IconButton(
                        onClick = {
                            passwordVisible = !passwordVisible
                        }
                    ) {
                        Icon(
                            imageVector = if (
                                passwordVisible
                            ) {
                                Icons.Outlined.VisibilityOff
                            } else {
                                Icons.Outlined.Visibility
                            },
                            contentDescription = null
                        )
                    }
                }
            } else {
                null
            },
            isError = error != null,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colors.primary,
                unfocusedBorderColor = colors.border,
                errorBorderColor = colors.error,
                focusedLabelColor = colors.primary,
                unfocusedLabelColor = colors.textSecondary,
                errorLabelColor = colors.error,
                cursorColor = colors.primary,
                focusedTextColor = colors.textPrimary,
                unfocusedTextColor = colors.textPrimary,
                focusedContainerColor = colors.surface,
                unfocusedContainerColor = colors.surface,
                errorContainerColor = colors.surface
            )
        )

        error?.let { errorResource ->
            Spacer(
                modifier = Modifier.height(6.dp)
            )
            Text(
                text = stringResource(errorResource),
                color = colors.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(
                    horizontal = 4.dp
                )
            )
        }
    }
}