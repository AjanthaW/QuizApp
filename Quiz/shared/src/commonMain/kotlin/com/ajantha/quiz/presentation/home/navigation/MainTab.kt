package com.ajantha.quiz.presentation.home.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainTab(
    val title: String,
    val icon: ImageVector
) {
    QUIZ(title = "Quiz", icon = Icons.Default.EmojiEvents),
    SETTINGS(title = "Settings", icon = Icons.Default.Settings),
    PROFILE(title = "Profile", icon = Icons.Default.Person)
}