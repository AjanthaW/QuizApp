package com.ajantha.quiz.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajantha.quiz.domain.usecase.CheckSessionUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val checkSessionUseCase: CheckSessionUseCase
) : ViewModel() {

    val state: StateFlow<SplashState>
        field = MutableStateFlow(SplashState())

    val effect: SharedFlow<SplashEffect>
        field = MutableSharedFlow<SplashEffect>()

    fun onIntent(intent: SplashIntent) {
        when (intent) {
            SplashIntent.CheckSession -> {
                checkSession()
            }
        }
    }

    private fun checkSession() {
        viewModelScope.launch {
            val session = checkSessionUseCase()
            state.update {
                it.copy(isChecking = false)
            }
            effect.emit(SplashEffect.SessionChecked(isLoggedIn = session != null))
        }
    }
}