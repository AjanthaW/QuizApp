package com.ajantha.quizserver.service

import com.ajantha.quizserver.dto.LoginRequest
import com.ajantha.quizserver.dto.LoginResponse
import com.ajantha.quizserver.dto.UserResponse
import com.ajantha.quizserver.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {

    fun login(request: LoginRequest): LoginResponse {
        val user =
            userRepository.findByEmail(request.email) ?: throw IllegalArgumentException("Invalid email or password")

        val passwordMatches = passwordEncoder.matches(
            request.password,
            user.password
        )

        if (!passwordMatches) {
            throw IllegalArgumentException("Invalid email or password")
        }

        val token = jwtService.generateToken(user.email)

        val profile = UserResponse(
            id = user.id,
            email = user.email,
            name = user.name,
            profileUrl = user.profileUrl,
            phone = user.phone,
            country = user.country,
            bio = user.bio
        )

        return LoginResponse(
            token = token,
            user = profile
        )
    }
}