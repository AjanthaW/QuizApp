package com.ajantha.quiz.presentation.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.ajantha.quiz.presentation.home.profile.ProfileRoute
import com.ajantha.quiz.presentation.home.quiz.QuizRoute
import com.ajantha.quiz.presentation.home.result.ResultRoute
import com.ajantha.quiz.presentation.home.settings.SettingsRoute
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

private val homeNavConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomeNavKey.Quiz::class, HomeNavKey.Quiz.serializer())
            subclass(HomeNavKey.Result::class, HomeNavKey.Result.serializer())
            subclass(HomeNavKey.Settings::class, HomeNavKey.Settings.serializer())
            subclass(HomeNavKey.Profile::class, HomeNavKey.Profile.serializer())
        }
    }
}

@Composable
fun HomeNavigation(
    currentTab: MainTab,
    onLogout: () -> Unit
) {
    val quizBackStack = rememberNavBackStack(
        homeNavConfig,
        HomeNavKey.Quiz()
    )

    val settingsBackStack = rememberNavBackStack(
        homeNavConfig,
        HomeNavKey.Settings
    )

    val profileBackStack = rememberNavBackStack(
        homeNavConfig,
        HomeNavKey.Profile
    )

    val currentBackStack = when (currentTab) {
        MainTab.QUIZ -> quizBackStack
        MainTab.SETTINGS -> settingsBackStack
        MainTab.PROFILE -> profileBackStack
    }

    NavDisplay(
        backStack = currentBackStack,
        onBack = {
            if (currentBackStack.size > 1) {
                currentBackStack.removeLastOrNull()
            }
        },
        entryProvider = entryProvider {
            entry<HomeNavKey.Quiz> { quiz ->
                QuizRoute(
                    quizId = quiz.id,
                    onOpenResult = { correctAnswers, totalQuestions ->
                        quizBackStack.add(
                            HomeNavKey.Result(
                                correctAnswers = correctAnswers,
                                totalQuestions = totalQuestions
                            )
                        )
                    }
                )
            }
            entry<HomeNavKey.Result> { result ->
                ResultRoute(
                    correctAnswers = result.correctAnswers,
                    totalQuestions = result.totalQuestions,
                    onStartAgain = {
                        quizBackStack.clear()
                        quizBackStack.add(
                            HomeNavKey.Quiz()
                        )
                    }
                )
            }
            entry<HomeNavKey.Settings> {
                SettingsRoute()
            }
            entry<HomeNavKey.Profile> {
                ProfileRoute(
                    onLogout = onLogout
                )
            }
        }
    )
}