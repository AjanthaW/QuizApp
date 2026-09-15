package com.ajantha.quiz.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajantha.quiz.domain.usecase.LoginUseCase
import com.ajantha.quiz.presentation.login.model.LoginUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import quiz.shared.generated.resources.Res
import quiz.shared.generated.resources.email_required
import quiz.shared.generated.resources.password_required

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    val uiState: StateFlow<LoginUiState>
        field = MutableStateFlow(LoginUiState())

    val effect: SharedFlow<LoginEffect>
        field = MutableSharedFlow()

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> {
                uiState.update {
                    it.copy(
                        email = intent.value,
                        emailError = null,
                        loginError = null
                    )
                }
            }

            is LoginIntent.PasswordChanged -> {
                uiState.update {
                    it.copy(
                        password = intent.value,
                        passwordError = null,
                        loginError = null
                    )
                }
            }

            LoginIntent.LoginClicked -> {
                validateAndLogin()
            }

            LoginIntent.Reset -> reset()
        }
    }

    private fun validateAndLogin() {
        val state = uiState.value

        if (state.isLoading) {
            return
        }

        val emailError =
            if (state.email.isBlank()) {
                Res.string.email_required
            } else {
                null
            }

        val passwordError =
            if (state.password.isBlank()) {
                Res.string.password_required
            } else {
                null
            }

        if (
            emailError != null || passwordError != null) {
            uiState.update {
                it.copy(
                    emailError = emailError,
                    passwordError = passwordError
                )
            }
            return
        }

        viewModelScope.launch {
            uiState.update {
                it.copy(
                    isLoading = true,
                    loginError = null
                )
            }

            runCatching {
                loginUseCase(
                    email = state.email.trim(),
                    password = state.password
                )
            }.onSuccess {
                uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }

                effect.emit(
                    LoginEffect.LoginSuccess
                )

            }.onFailure { exception ->
                uiState.update {
                    it.copy(
                        isLoading = false,
                        loginError = exception.message ?: "Login failed"
                    )
                }
            }
        }
    }

    private fun reset() {
        uiState.value = LoginUiState()
    }

}