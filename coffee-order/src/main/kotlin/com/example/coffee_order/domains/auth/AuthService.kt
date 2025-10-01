package com.example.coffee_order.domains.auth

import com.example.coffee_order.domains.auth.dto.RegisterRequestDto
import com.example.coffee_order.domains.user.UserRepository
import com.example.coffee_order.domains.user.entities.User
import org.apache.coyote.BadRequestException
import org.springframework.security.crypto.password.PasswordEncoder

class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    // 회원가입
    fun register(registerRequestDto: RegisterRequestDto) {
        if (userRepository.existsByEmail(registerRequestDto.email)) {
            throw BadRequestException("이미 존재하는 이메일입니다.")
        }

        val hashedPassword = passwordEncoder.encode(registerRequestDto.password)
        val user = User(
            email = registerRequestDto.email,
            username = registerRequestDto.username,
            password = hashedPassword,
            refreshToken = "null"
        )
        userRepository.save<User>(user)
    }
}