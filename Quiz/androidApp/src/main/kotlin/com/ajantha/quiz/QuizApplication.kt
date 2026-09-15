package com.ajantha.quiz

import android.app.Application
import com.ajantha.quiz.core.session.initializeSessionStorage
import com.ajantha.quiz.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class QuizApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeSessionStorage(this)
        startKoin {
            androidContext(this@QuizApplication)
            modules(
                appModules
            )
        }
    }
}