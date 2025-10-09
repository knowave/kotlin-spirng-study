package com.example.coffee_order.domains.user

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import com.example.coffee_order.common.config.UserPrincipal
import com.example.coffee_order.domains.user.dtos.UserPointResponse

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/point")
    fun getUserPoint(@AuthenticationPrincipal principal: UserPrincipal): ResponseEntity<UserPointResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserPoint(principal.id))
    }
}