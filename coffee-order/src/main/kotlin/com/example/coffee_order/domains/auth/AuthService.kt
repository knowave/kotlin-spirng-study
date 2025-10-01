package com.example.coffee_order.domains.auth

import com.example.coffee_order.common.config.JwtProvider
import com.example.coffee_order.domains.auth.dto.LoginRequestDto
import com.example.coffee_order.domains.auth.dto.RegisterRequestDto
import com.example.coffee_order.domains.auth.dto.TokenResponse
import com.example.coffee_order.domains.user.UserRepository
import com.example.coffee_order.domains.user.entities.User
import org.apache.coyote.BadRequestException
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.security.crypto.password.PasswordEncoder

class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider
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

    // 로그인
    fun login(loginRequestDto: LoginRequestDto): TokenResponse {
        val user = userRepository.findByEmail(loginRequestDto.email)
            ?: throw ChangeSetPersister.NotFoundException("존재하지 않는 user.")

        if (!passwordEncoder.matches(loginRequestDto.password, user.password)) {
            throw BadRequestException("이메일 또는 비밀번호가 올바르지 않습니다.")
        }

        return TokenResponse(jwtProvider.generateToken(user.id, user.email))
    }
}