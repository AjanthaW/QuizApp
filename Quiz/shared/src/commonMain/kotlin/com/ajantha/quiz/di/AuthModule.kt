package com.ajantha.quiz.di

import com.ajantha.quiz.data.remote.AuthApi
import com.ajantha.quiz.data.remote.AuthApiImpl
import com.ajantha.quiz.data.repository.AuthRepositoryImpl
import com.ajantha.quiz.domain.repository.AuthRepository
import com.ajantha.quiz.domain.usecase.LoginUseCase
import com.ajantha.quiz.domain.usecase.LogoutUseCase
import com.ajantha.quiz.presentation.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    single<AuthApi> {
        AuthApiImpl(client = get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            api = get(),
            sessionStorage = get()
        )
    }

    factory {
        LoginUseCase(repository = get())
    }

    factory {
        LogoutUseCase(repository = get())
    }

    viewModel {
        LoginViewModel(loginUseCase = get())
    }

}