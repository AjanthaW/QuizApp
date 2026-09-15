package com.ajantha.quiz.presentation.home.quiz.model

import com.ajantha.quiz.domain.model.QuizMode

data class QuizUiState(
    val isLoading: Boolean = false,
    val isSubmitting: Boolean = false,
    val mode: QuizMode = QuizMode.BINARY,
    val question: QuizQuestionModel? = null,
    val questionNumber: Int = 0,
    val totalQuestions: Int = 10,
    val correctAnswers: Int = 0,
    val selectedAnswer: String? = null,
    val showAnswerDialog: Boolean = false,
    val answerCorrect: Boolean = false,
    val correctAnswer: String = "",
    val completed: Boolean = false,
    val errorMessage: String? = null
) {
    val progress: Float
        get() {
            if (totalQuestions == 0) return 0f
            return questionNumber.toFloat() / totalQuestions.toFloat()
        }
}