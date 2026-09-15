package com.ajantha.quiz.core.theme

import androidx.compose.ui.graphics.Color

data class AppColors(
    val primary: Color,
    val onPrimary: Color,
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val border: Color,
    val success: Color,
    val onSuccess: Color,
    val error: Color,
    val onError: Color
)

val LightAppColors = AppColors(
    primary = Color(0xFF5B4DFF),
    onPrimary = Color.White,
    background = Color(0xFFF8F7FC),
    surface = Color.White,
    surfaceVariant = Color(0xFFF0EFF7),
    textPrimary = Color(0xFF171525),
    textSecondary = Color(0xFF77748A),
    border = Color(0xFFDAD8E5),
    success = Color(0xFF2E7D32),
    onSuccess = Color.White,
    error = Color(0xFFFF0000),
    onError = Color.White
)

val DarkAppColors = AppColors(
    primary = Color(0xFF9B91FF),
    onPrimary = Color(0xFF211A5C),
    background = Color(0xFF121118),
    surface = Color(0xFF1C1B22),
    surfaceVariant = Color(0xFF292832),
    textPrimary = Color(0xFFF3F1FA),
    textSecondary = Color(0xFFB8B5C4),
    border = Color(0xFF45434F),
    success = Color(0xFF66BB6A),
    onSuccess = Color(0xFF102413),
    error = Color(0xFFFF6B6B),
    onError = Color(0xFF3B0000)
)