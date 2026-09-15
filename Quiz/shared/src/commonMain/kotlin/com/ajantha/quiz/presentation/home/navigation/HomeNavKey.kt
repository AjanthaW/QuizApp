package com.ajantha.quiz.presentation.home.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
sealed interface HomeNavKey : NavKey {

    @OptIn(ExperimentalUuidApi::class)
    @Serializable
    data class Quiz(
        val id: String = Uuid.random().toString()
    ) : HomeNavKey

    @Serializable
    data class Result(
        val correctAnswers: Int,
        val totalQuestions: Int
    ) : HomeNavKey

    @Serializable
    data object Settings : HomeNavKey

    @Serializable
    data object Profile : HomeNavKey
}