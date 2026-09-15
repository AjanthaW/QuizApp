package com.ajantha.quiz.data.store

import com.ajantha.quiz.domain.model.QuizMode
import com.ajantha.quiz.domain.store.QuizModeStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuizModeStoreImpl : QuizModeStore {

    override val mode: StateFlow<QuizMode>
        field = MutableStateFlow(QuizMode.BINARY)

    override fun setMode(mode: QuizMode) {
        this.mode.value = mode
    }

}