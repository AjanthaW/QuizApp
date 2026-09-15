package com.ajantha.quiz.core.network

class ApiException(
    val code: Int,
    override val message: String
) : Exception(message)