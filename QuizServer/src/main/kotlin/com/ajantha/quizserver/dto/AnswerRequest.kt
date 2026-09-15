package com.ajantha.quizserver.dto

data class AnswerRequest(
    val questionId: Long,
    val answer: String
)