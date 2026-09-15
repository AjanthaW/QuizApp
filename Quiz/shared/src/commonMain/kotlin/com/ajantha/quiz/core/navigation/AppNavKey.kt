package com.ajantha.quiz.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavKey : NavKey {

    @Serializable
    data object Splash : AppNavKey

    @Serializable
    data object Login : AppNavKey

    @Serializable
    data object Home : AppNavKey

}