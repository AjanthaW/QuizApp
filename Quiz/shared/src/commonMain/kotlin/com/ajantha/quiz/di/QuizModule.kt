package com.ajantha.quiz.di

import com.ajantha.quiz.data.remote.QuizApi
import com.ajantha.quiz.data.remote.QuizApiImpl
import com.ajantha.quiz.data.repository.QuizRepositoryImpl
import com.ajantha.quiz.domain.repository.QuizRepository
import com.ajantha.quiz.domain.usecase.StartQuizUseCase
import com.ajantha.quiz.domain.usecase.SubmitAnswerUseCase
import com.ajantha.quiz.presentation.home.quiz.QuizViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val quizModule = module {

    single<QuizApi> {
        QuizApiImpl(client = get())
    }

    single<QuizRepository> {
        QuizRepositoryImpl(api = get())
    }

    factory {
        StartQuizUseCase(repository = get())
    }

    factory {
        SubmitAnswerUseCase(repository = get())
    }

    viewModel {
        QuizViewModel(
            startQuizUseCase = get(),
            submitAnswerUseCase = get(),
            quizModeStore = get()
        )
    }

}