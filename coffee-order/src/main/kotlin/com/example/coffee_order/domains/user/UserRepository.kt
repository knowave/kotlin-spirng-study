package com.example.coffee_order.domains.user

import com.example.coffee_order.domains.user.entities.User

interface UserRepository {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}