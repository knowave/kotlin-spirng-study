package com.example.coffee_order.domains.user

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import com.example.coffee_order.common.config.UserPrincipal
import com.example.coffee_order.domains.user.dtos.UserPointResponse
import com.example.coffee_order.domains.user.dtos.UserPointRequestDto

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/point")
    fun getUserPoint(@AuthenticationPrincipal principal: UserPrincipal): ResponseEntity<UserPointResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserPoint(principal.id))
    }

    @PatchMapping("/point")
    fun updateUserPoint(@AuthenticationPrincipal principal: UserPrincipal, @RequestBody dto: UserPointRequestDto): ResponseEntity<Boolean> {
        userService.updateUserPoint(principal.id, dto.point)
        return ResponseEntity.status(HttpStatus.OK).body(true)
    }
}