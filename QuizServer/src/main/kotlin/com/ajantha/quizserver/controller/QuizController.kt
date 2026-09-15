package com.ajantha.quizserver.controller

import com.ajantha.quizserver.dto.AnswerRequest
import com.ajantha.quizserver.dto.AnswerResponse
import com.ajantha.quizserver.dto.QuizSessionResponse
import com.ajantha.quizserver.quiz.QuizMode
import com.ajantha.quizserver.service.QuizService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/quiz")
class QuizController(
    private val quizService: QuizService
) {
    @GetMapping("/session")
    fun createSession(
        @RequestParam mode: QuizMode,
        authentication: Authentication
    ): ResponseEntity<QuizSessionResponse> {
        val response = quizService.createSession(
            email = authentication.name,
            mode = mode
        )
        return ResponseEntity.ok(response)
    }

    @PostMapping("/{sessionId}/answer")
    fun submitAnswer(
        @PathVariable sessionId: String,
        @RequestBody request: AnswerRequest,
        authentication: Authentication
    ): ResponseEntity<AnswerResponse> {
        val response = quizService.submitAnswer(
            email = authentication.name,
            sessionId = sessionId,
            request = request
        )
        return ResponseEntity.ok(response)
    }
}