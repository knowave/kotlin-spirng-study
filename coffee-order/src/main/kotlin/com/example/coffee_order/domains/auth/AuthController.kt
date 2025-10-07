package com.example.coffee_order.domains.auth

import com.example.coffee_order.domains.auth.dto.RegisterRequestDto
import com.example.coffee_order.domains.auth.dto.LoginRequestDto
import com.example.coffee_order.domains.auth.dto.TokenResponse
import com.example.coffee_order.domains.auth.dto.MyInfoResponseDto
import com.example.coffee_order.common.config.UserPrincipal
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.security.core.annotation.AuthenticationPrincipal

@RestController
@RequestMapping("api/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/register")
    fun register(@Valid @RequestBody requestDto: RegisterRequestDto): ResponseEntity<Boolean> {
        authService.register(requestDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(true)
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody requestDto: LoginRequestDto): ResponseEntity<TokenResponse> {
        val token = authService.login(requestDto)
        return ResponseEntity.status(HttpStatus.OK).body(token)
    }

    @GetMapping("/my-info")
    fun getMyInfo(@AuthenticationPrincipal principal: UserPrincipal): ResponseEntity<MyInfoResponseDto> {
        val myInfo = authService.getMyInfo(principal.id)
        return ResponseEntity.status(HttpStatus.OK).body(myInfo)
    }
}