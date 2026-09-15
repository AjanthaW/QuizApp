package com.ajantha.quiz.core.network

import com.ajantha.quiz.core.session.SessionStorage
import com.ajantha.quiz.data.remote.model.ErrorResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(
    sessionStorage: SessionStorage
): HttpClient {

    return HttpClient(
        platformHttpEngine()
    ) {
        expectSuccess = false

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    explicitNulls = false
                }
            )
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 15_000
            connectTimeoutMillis = 15_000
            socketTimeoutMillis = 15_000
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY // TODO: Remove in production
        }

        install(Auth) {
            bearer {
                loadTokens {
                    val token = sessionStorage.getToken()
                    if (token != null) {
                        BearerTokens(
                            accessToken = token,
                            refreshToken = ""
                        )
                    } else {
                        null
                    }
                }

                sendWithoutRequest { request ->
                    request.url.encodedPath != "/api/auth/login"
                }
            }
        }

        HttpResponseValidator {
            validateResponse { response ->
                if (!response.status.isSuccess()) {
                    val error = runCatching {
                        response.body<ErrorResponse>()
                    }.getOrNull()
                    throw ApiException(
                        code = response.status.value,
                        message = error?.message ?: "Request failed"
                    )
                }
            }
        }

        defaultRequest {
            url(NetworkConfig.BASE_URL)
            contentType(
                ContentType.Application.Json
            )
        }
    }
}