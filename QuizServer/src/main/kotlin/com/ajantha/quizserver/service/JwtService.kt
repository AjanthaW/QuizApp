package com.ajantha.quizserver.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService(

    @Value("\${app.jwt.secret}")
    private val secret: String,

    @Value("\${app.jwt.expiration}")
    private val expiration: Long
) {

    private val signingKey: SecretKey
        get() = Keys.hmacShaKeyFor(
            secret.toByteArray(StandardCharsets.UTF_8)
        )

    fun generateToken(email: String): String {
        val now = Date()
        val expiryDate = Date(now.time + expiration)

        return Jwts.builder()
            .subject(email)
            .issuedAt(now)
            .expiration(expiryDate)
            .signWith(signingKey)
            .compact()
    }

    fun extractEmail(token: String): String? {
        return try {
            Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .payload
                .subject
        } catch (e: Exception) {
            null
        }
    }
}