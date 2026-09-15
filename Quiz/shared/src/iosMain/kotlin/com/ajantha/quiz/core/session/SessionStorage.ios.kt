package com.ajantha.quiz.core.session

import kotlinx.serialization.json.Json
import platform.Foundation.NSUserDefaults

actual fun createSessionStorage(): SessionStorage {
    return IosSessionStorage()
}

private class IosSessionStorage : SessionStorage {

    private val defaults = NSUserDefaults.standardUserDefaults

    private val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun saveSession(session: UserSession) {
        defaults.setObject(
            value = json.encodeToString(session),
            forKey = KEY_SESSION
        )
    }

    override suspend fun getSession(): UserSession? {
        val sessionJson = defaults.stringForKey(KEY_SESSION) ?: return null

        return runCatching {
            json.decodeFromString<UserSession>(sessionJson)
        }.getOrNull()
    }

    override suspend fun getToken(): String? {
        return getSession()?.token
    }

    override suspend fun clearSession() {
        defaults.removeObjectForKey(KEY_SESSION)
    }

    private companion object {
        const val KEY_SESSION = "user_session"
    }
}