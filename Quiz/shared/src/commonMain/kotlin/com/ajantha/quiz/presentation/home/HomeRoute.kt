package com.ajantha.quiz.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ajantha.quiz.presentation.home.navigation.HomeNavigation
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoute(
    onLogout: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onIntent = viewModel::onIntent
    ) {
        HomeNavigation(
            currentTab = state.currentTab,
            onLogout = onLogout
        )
    }
}