package com.example.coffee_order.domains.user

import com.example.coffee_order.domains.user.UserRepository
import com.example.coffee_order.domains.user.dtos.UserPointResponse
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getUserPoint(userId: Long): UserPointResponse {
        val user = userRepository.findById(userId)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "User not found") }

        return UserPointResponse(user.id!!, user.point)
    }

    fun updateUserPoint(userId: Long, point: Long) {
        val user = userRepository.findById(userId)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "User not found") }

        user.point += point
        userRepository.save(user)
    }
}