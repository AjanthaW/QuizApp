package com.ajantha.quizserver.quiz

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class QuizSessionStore {

    private val sessions = ConcurrentHashMap<String, QuizSession>()

    fun save(session: QuizSession): QuizSession {
        sessions[session.id] = session
        return session
    }

    fun get(sessionId: String): QuizSession? {
        return sessions[sessionId]
    }

    fun remove(sessionId: String) {
        sessions.remove(sessionId)
    }
}