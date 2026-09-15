package com.ajantha.quiz.data.repository

import com.ajantha.quiz.core.mapper.toDomain
import com.ajantha.quiz.data.remote.QuizApi
import com.ajantha.quiz.domain.model.Answer
import com.ajantha.quiz.domain.model.QuizMode
import com.ajantha.quiz.domain.model.QuizSession
import com.ajantha.quiz.domain.repository.QuizRepository

class QuizRepositoryImpl(
    private val api: QuizApi
) : QuizRepository {

    override suspend fun createSession(
        mode: QuizMode
    ): QuizSession {
        return api.getQuiz(mode).toDomain()
    }

    override suspend fun submitAnswer(
        sessionId: String,
        questionId: Long,
        answer: String
    ): Answer {
        return api.submitAnswer(
            sessionId = sessionId,
            questionId = questionId,
            answer = answer
        ).toDomain()
    }

}