package com.example.coffee_order.domains.auth.dto

import jakarta.validation.constraints.*

data class RegisterRequestDto(
    @field:Email
    val email: String,

    @field:NotBlank
    val username: String,

    @field:Size(min = 8, max = 64)
    val password: String
)

data class LoginRequestDto(
    @field:Email val email: String,
    @field:NotBlank val password: String
)

data class TokenResponse(
    val token: String
)