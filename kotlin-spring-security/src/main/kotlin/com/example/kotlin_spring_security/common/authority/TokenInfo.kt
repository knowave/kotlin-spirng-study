package com.example.kotlin_spring_security.common.authority

data class TokenInfo(
    val grantType: String,
    val accessToken: String,
)
