package com.ajantha.quiz.di

import com.ajantha.quiz.presentation.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {

    viewModel {
        SplashViewModel(checkSessionUseCase = get())
    }

}