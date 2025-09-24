package com.example.coffee_order.common.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthFilter(
    private val jwtProvider: JwtProvider
): OncePerRequestFilter() {
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
                val email = claims["email"] as String

                val principal = UserPrincipal(userId, email)
                val authToken = UsernamePasswordAuthenticationToken(
                    principal,
                    null,
                    emptyList()
                )
            } catch (_: Exception) {
                // 토큰 검증 실패 → 무시하고 다음 필터로
            }
        }

        filterChain.doFilter(request, response)
    }
}

data class UserPrincipal(
    val id: Long,
    val email: String
)