package com.ajantha.quiz.presentation.home

import androidx.lifecycle.ViewModel
import com.ajantha.quiz.presentation.home.navigation.MainTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    val uiState: StateFlow<HomeUiState>
        field = MutableStateFlow(HomeUiState())

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.SelectTab -> {
                selectTab(intent.tab)
            }
        }
    }

    private fun selectTab(tab: MainTab) {
        if (uiState.value.currentTab == tab) return

        uiState.update {
            it.copy(
                currentTab = tab
            )
        }
    }
}