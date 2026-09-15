package com.ajantha.quiz.data.remote

import com.ajantha.quiz.data.remote.model.LoginRequest
import com.ajantha.quiz.data.remote.model.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthApiImpl(
    private val client: HttpClient
) : AuthApi {

    override suspend fun login(
        email: String,
        password: String
    ): LoginResponse {
        return client.post("/api/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(
                LoginRequest(
                    email = email,
                    password = password
                )
            )
        }.body()
    }
}