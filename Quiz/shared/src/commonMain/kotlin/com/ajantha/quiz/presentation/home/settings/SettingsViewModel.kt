package com.ajantha.quiz.presentation.home.settings

import androidx.lifecycle.ViewModel
import com.ajantha.quiz.domain.model.QuizMode
import com.ajantha.quiz.domain.store.QuizModeStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SettingsViewModel(
    private val quizModeStore: QuizModeStore
) : ViewModel() {

    val uiState: StateFlow<SettingsUiState>
        field = MutableStateFlow(
            SettingsUiState(
                mode = quizModeStore.mode.value
            )
        )

    fun onIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.SetMode -> setMode(intent.mode)
        }
    }

    private fun setMode(mode: QuizMode) {
        if (mode == uiState.value.mode) return
        quizModeStore.setMode(mode)
        uiState.update {
            it.copy(mode = mode)
        }
    }
}