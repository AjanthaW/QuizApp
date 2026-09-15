package com.ajantha.quizserver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TestController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Famous Quote Quiz Server is running!"
    }
}