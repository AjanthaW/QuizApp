package com.ajantha.quiz.data.remote

import com.ajantha.quiz.data.remote.model.AnswerRequest
import com.ajantha.quiz.data.remote.model.AnswerResponse
import com.ajantha.quiz.data.remote.model.QuizSessionResponse
import com.ajantha.quiz.domain.model.QuizMode
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class QuizApiImpl(
    private val client: HttpClient
) : QuizApi {

    override suspend fun getQuiz(
        mode: QuizMode
    ): QuizSessionResponse {
        return client.get("/api/quiz/session") {
            contentType(ContentType.Application.Json)
            parameter("mode", mode.name)
        }.body()
    }

    override suspend fun submitAnswer(
        sessionId: String,
        questionId: Long,
        answer: String
    ): AnswerResponse {
        return client.post(
            "/api/quiz/$sessionId/answer"
        ) {
            contentType(ContentType.Application.Json)
            setBody(
                AnswerRequest(
                    questionId = questionId,
                    answer = answer
                )
            )
        }.body()
    }

}
