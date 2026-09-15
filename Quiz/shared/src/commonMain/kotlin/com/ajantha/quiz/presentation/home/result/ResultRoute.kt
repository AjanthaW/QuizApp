package com.ajantha.quiz.presentation.home.result

import androidx.compose.runtime.Composable

@Composable
fun ResultRoute(
    correctAnswers: Int,
    totalQuestions: Int,
    onStartAgain: () -> Unit
) {
    ResultScreen(
        correctAnswers = correctAnswers,
        totalQuestions = totalQuestions,
        onStartAgain = onStartAgain
    )
}