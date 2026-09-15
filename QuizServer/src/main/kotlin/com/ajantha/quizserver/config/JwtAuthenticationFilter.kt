package com.ajantha.quizserver.config

import com.ajantha.quizserver.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorization = request.getHeader("Authorization")

        if (
            authorization != null &&
            authorization.startsWith("Bearer ")
        ) {
            val token = authorization.removePrefix("Bearer ").trim()
            val email = jwtService.extractEmail(token)

            if (email != null && SecurityContextHolder.getContext().authentication == null) {
                val authentication = UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    emptyList()
                )

                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        filterChain.doFilter(
            request,
            response
        )
    }
}