package com.ajantha.quiz.domain.store

import com.ajantha.quiz.domain.model.QuizMode
import kotlinx.coroutines.flow.StateFlow

interface QuizModeStore {
    val mode: StateFlow<QuizMode>
    fun setMode(mode: QuizMode)
}