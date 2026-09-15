package com.ajantha.quiz.presentation.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajantha.quiz.domain.usecase.GetProfileUseCase
import com.ajantha.quiz.domain.usecase.LogoutUseCase
import com.ajantha.quiz.presentation.home.profile.model.ProfileUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val uiState: StateFlow<ProfileUiState>
        field = MutableStateFlow(ProfileUiState())

    val effect: SharedFlow<ProfileEffect>
        field = MutableSharedFlow(extraBufferCapacity = 1)

    fun onIntent(intent: ProfileIntent) {
        when (intent) {
            ProfileIntent.Load -> loadProfile()
            ProfileIntent.Logout -> logout()
        }
    }

    private fun loadProfile() {
        if (uiState.value.isLoading) return

        viewModelScope.launch {
            uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            runCatching {
                getProfileUseCase()
            }.onSuccess { user ->
                if (user == null) {
                    uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "User session not found"
                        )
                    }
                    return@onSuccess
                }

                uiState.update {
                    it.copy(
                        isLoading = false,
                        user = user
                    )
                }
            }.onFailure { error ->
                uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Failed to load profile"
                    )
                }
            }
        }
    }

    private fun logout() {
        if (uiState.value.isLoading) return

        viewModelScope.launch {
            uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            runCatching {
                logoutUseCase()
            }.onSuccess {
                uiState.update {
                    it.copy(
                        isLoading = false,
                        user = null
                    )
                }

                effect.emit(
                    ProfileEffect.NavigateToLogin
                )
            }.onFailure { error ->
                uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Logout failed"
                    )
                }
            }
        }
    }
}