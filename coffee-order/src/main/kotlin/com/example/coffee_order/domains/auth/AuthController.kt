package com.example.coffee_order.domains.auth

import com.example.coffee_order.domains.user.UserRepository

class AuthController(
    private val userRepository: UserRepository
) {
}