package com.ajantha.quiz.di

import com.ajantha.quiz.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel {
        HomeViewModel()
    }

}