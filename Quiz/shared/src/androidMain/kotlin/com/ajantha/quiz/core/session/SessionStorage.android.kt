package com.ajantha.quiz.core.session

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.serialization.json.Json

private lateinit var applicationContext: Context

fun initializeSessionStorage(context: Context) {
    applicationContext = context.applicationContext
}

actual fun createSessionStorage(): SessionStorage {
    check(::applicationContext.isInitialized) {
        "SessionStorage has not been initialized."
    }
    return AndroidSessionStorage(applicationContext)
}

private class AndroidSessionStorage(
    context: Context
) : SessionStorage {

    private val preferences: SharedPreferences = context.getSharedPreferences(
        "quiz_session",
        Context.MODE_PRIVATE
    )

    private val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun saveSession(session: UserSession) {
        preferences.edit {
            putString(
                KEY_SESSION,
                json.encodeToString(session)
            )
        }
    }

    override suspend fun getSession(): UserSession? {
        val sessionJson = preferences.getString(KEY_SESSION, null) ?: return null

        return runCatching {
            json.decodeFromString<UserSession>(sessionJson)
        }.getOrNull()
    }

    override suspend fun getToken(): String? {
        return getSession()?.token
    }

    override suspend fun clearSession() {
        preferences.edit {
            remove(KEY_SESSION)
        }
    }

    private companion object {
        const val KEY_SESSION = "user_session"
    }
}