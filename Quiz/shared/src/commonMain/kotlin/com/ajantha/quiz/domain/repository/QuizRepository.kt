package com.ajantha.quiz.domain.repository

import com.ajantha.quiz.domain.model.Answer
import com.ajantha.quiz.domain.model.QuizMode
import com.ajantha.quiz.domain.model.QuizSession

interface QuizRepository {

    suspend fun createSession(
        mode: QuizMode
    ): QuizSession

    suspend fun submitAnswer(
        sessionId: String,
        questionId: Long,
        answer: String
    ): Answer

}