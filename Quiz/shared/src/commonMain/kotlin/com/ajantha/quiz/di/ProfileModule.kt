package com.ajantha.quiz.di

import com.ajantha.quiz.data.repository.ProfileRepositoryImpl
import com.ajantha.quiz.domain.repository.ProfileRepository
import com.ajantha.quiz.domain.usecase.GetProfileUseCase
import com.ajantha.quiz.domain.usecase.LogoutUseCase
import com.ajantha.quiz.presentation.home.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {

    single<ProfileRepository> {
        ProfileRepositoryImpl(sessionStorage = get())
    }

    factory {
        GetProfileUseCase(repository = get())
    }

    factory {
        LogoutUseCase(repository = get())
    }

    viewModel {
        ProfileViewModel(
            getProfileUseCase = get(),
            logoutUseCase = get()
        )
    }

}