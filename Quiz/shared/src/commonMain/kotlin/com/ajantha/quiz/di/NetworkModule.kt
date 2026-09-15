package com.ajantha.quiz.di

import com.ajantha.quiz.core.network.createHttpClient
import org.koin.dsl.module

val networkModule = module {

    single {
        createHttpClient(sessionStorage = get())
    }

}