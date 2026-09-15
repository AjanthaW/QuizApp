package com.ajantha.quizserver.config

import com.ajantha.quizserver.entity.Quote
import com.ajantha.quizserver.entity.User
import com.ajantha.quizserver.repository.QuoteRepository
import com.ajantha.quizserver.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class DataInitializer {
    @Bean
    fun initializeUsers(
        userRepository: UserRepository,
        passwordEncoder: PasswordEncoder
    ) = CommandLineRunner {
        if (userRepository.count() == 0L) {
            userRepository.save(
                User(
                    email = "alex@quote.com",
                    password = requireNotNull(passwordEncoder.encode("Pass@123")),
                    name = "Alex Smith",
                    profileUrl = "https://i.pravatar.cc/300?img=53",
                    phone = "+442333555777",
                    country = "United Kingdom",
                    bio = "London trivia enthusiast"
                )
            )
        }
    }

    @Bean
    fun initializeQuotes(
        quoteRepository: QuoteRepository
    ) = CommandLineRunner {
        if (quoteRepository.count() == 0L) {
            quoteRepository.saveAll(
                listOf(
                    Quote(
                        quote = "The only source of knowledge is experience.",
                        author = "Albert Einstein"
                    ),
                    Quote(
                        quote = "It always seems impossible until it's done.",
                        author = "Nelson Mandela"
                    ),
                    Quote(
                        quote = "The future belongs to those who believe in the beauty of their dreams.",
                        author = "Eleanor Roosevelt"
                    ),
                    Quote(
                        quote = "In the middle of difficulty lies opportunity.",
                        author = "Albert Einstein"
                    ),
                    Quote(
                        quote = "Success is not final, failure is not fatal.",
                        author = "Winston Churchill"
                    ),
                    Quote(
                        quote = "The only way to do great work is to love what you do.",
                        author = "Steve Jobs"
                    ),
                    Quote(
                        quote = "Life is what happens when you're busy making other plans.",
                        author = "John Lennon"
                    ),
                    Quote(
                        quote = "Do what you can, with what you have, where you are.",
                        author = "Theodore Roosevelt"
                    ),
                    Quote(
                        quote = "Everything you can imagine is real.",
                        author = "Pablo Picasso"
                    ),
                    Quote(
                        quote = "Turn your wounds into wisdom.",
                        author = "Oprah Winfrey"
                    ),
                    Quote(
                        quote = "The journey of a thousand miles begins with one step.",
                        author = "Lao Tzu"
                    ),
                    Quote(
                        quote = "That which does not kill us makes us stronger.",
                        author = "Friedrich Nietzsche"
                    )
                )
            )
        }
    }
}