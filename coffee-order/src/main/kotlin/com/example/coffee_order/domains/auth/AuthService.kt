package com.example.coffee_order.domains.auth

import com.example.coffee_order.common.config.JwtProvider
import com.example.coffee_order.domains.auth.dto.LoginRequestDto
import com.example.coffee_order.domains.auth.dto.RegisterRequestDto
import com.example.coffee_order.domains.auth.dto.TokenResponse
import com.example.coffee_order.domains.auth.dto.MyInfoResponseDto
import com.example.coffee_order.domains.user.UserRepository
import com.example.coffee_order.domains.user.entities.User as UserEntity
import org.apache.coyote.BadRequestException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus

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
        val user = UserEntity(
            email = registerRequestDto.email,
            username = registerRequestDto.username,
            password = hashedPassword,
            refreshToken = "null"
        )
        userRepository.save<UserEntity>(user)
    }

    // 로그인
    fun login(loginRequestDto: LoginRequestDto): TokenResponse {
        val user = userRepository.findByEmail(loginRequestDto.email)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")

        if (!passwordEncoder.matches(loginRequestDto.password, user.password)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials")
        }

        val userId: Long = user.id ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User id is null")
        
        return TokenResponse(jwtProvider.generateToken(userId, user.email))
    }

    // 내 정보 조회
    fun getMyInfo(userId: Long): MyInfoResponseDto {
        val user = userRepository.findById(userId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User not found") }
        return MyInfoResponseDto(user!!.id, user.email, user.username)
    }
}