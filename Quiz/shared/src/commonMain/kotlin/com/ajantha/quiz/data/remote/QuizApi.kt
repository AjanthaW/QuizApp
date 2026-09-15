package com.ajantha.quiz.data.remote

import com.ajantha.quiz.data.remote.model.AnswerResponse
import com.ajantha.quiz.data.remote.model.QuizSessionResponse
import com.ajantha.quiz.domain.model.QuizMode

interface QuizApi {

    suspend fun getQuiz(
        mode: QuizMode
    ): QuizSessionResponse

    suspend fun submitAnswer(
        sessionId: String,
        questionId: Long,
        answer: String
    ): AnswerResponse

}