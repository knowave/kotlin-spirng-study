package com.example.coffee_order.domains.order

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import com.example.coffee_order.common.config.UserPrincipal
import com.example.coffee_order.domains.order.dtos.CreateOrderRequestDto
import com.example.coffee_order.domains.order.dtos.CreateOrderResponseDto

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping()
    fun createOrder(@AuthenticationPrincipal principal: UserPrincipal, @RequestBody dto: CreateOrderRequestDto): ResponseEntity<CreateOrderResponseDto> {
        val order = orderService.createOrder(principal.id, dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(order)
    }
}