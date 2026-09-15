package com.ajantha.quiz.di

import com.ajantha.quiz.data.store.QuizModeStoreImpl
import com.ajantha.quiz.domain.store.QuizModeStore
import com.ajantha.quiz.presentation.home.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {

    single<QuizModeStore> {
        QuizModeStoreImpl()
    }

    viewModel {
        SettingsViewModel(quizModeStore = get())
    }

}