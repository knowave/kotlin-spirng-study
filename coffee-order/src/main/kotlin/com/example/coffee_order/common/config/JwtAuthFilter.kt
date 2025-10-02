package com.example.coffee_order.common.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthFilter(
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val auth = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (auth?.startsWith("Bearer ") == true) {
            val token = auth.removePrefix("Bearer ").trim()
            try {
                val claims = jwtProvider.parse(token).payload
                val userId = claims.subject.toLong()
                val email = claims["email"]?.toString() ?: ""

                val principal = UserPrincipal(userId, email)
                val authToken = UsernamePasswordAuthenticationToken(
                    principal,
                    null,
                    emptyList()
                )
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            } catch (_: Exception) {
                SecurityContextHolder.clearContext()
            }
        }

        filterChain.doFilter(request, response)
    }
}

data class UserPrincipal(
    val id: Long,
    val email: String
)