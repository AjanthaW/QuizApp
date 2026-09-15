package com.ajantha.quiz.di

import com.ajantha.quiz.core.session.SessionStorage
import com.ajantha.quiz.core.session.createSessionStorage
import com.ajantha.quiz.domain.usecase.CheckSessionUseCase
import org.koin.dsl.module

val sessionModule = module {

    single<SessionStorage> {
        createSessionStorage()
    }

    factory {
        CheckSessionUseCase(repository = get())
    }

}