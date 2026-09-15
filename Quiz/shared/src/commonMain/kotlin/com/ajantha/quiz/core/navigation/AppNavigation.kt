package com.ajantha.quiz.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.savedstate.serialization.SavedStateConfiguration
import com.ajantha.quiz.presentation.home.HomeRoute
import com.ajantha.quiz.presentation.login.LoginRoute
import com.ajantha.quiz.presentation.splash.SplashRoute
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

private val appNavConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(AppNavKey.Splash::class, AppNavKey.Splash.serializer())
            subclass(AppNavKey.Login::class, AppNavKey.Login.serializer())
            subclass(AppNavKey.Home::class, AppNavKey.Home.serializer())
        }
    }
}

@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(
        appNavConfig,
        AppNavKey.Splash
    )

    val currentRoute = backStack.lastOrNull()
    when (currentRoute) {
        AppNavKey.Splash -> {
            SplashRoute(
                onSessionChecked = { isLoggedIn ->
                    backStack.clear()
                    backStack.add(
                        if (isLoggedIn) {
                            AppNavKey.Home
                        } else {
                            AppNavKey.Login
                        }
                    )
                }
            )
        }

        AppNavKey.Login -> {
            LoginRoute(
                onLoginSuccess = {
                    backStack.clear()
                    backStack.add(
                        AppNavKey.Home
                    )
                }
            )
        }

        AppNavKey.Home -> {
            HomeRoute(
                onLogout = {
                    backStack.clear()
                    backStack.add(
                        AppNavKey.Login
                    )
                }
            )
        }
    }
}