package com.knowave.spring_boot_study.domains.order.controller

import com.knowave.spring_boot_study.domains.order.controller.dto.CreateOrderRequest
import com.knowave.spring_boot_study.domains.order.controller.dto.GetOrderResponse
import com.knowave.spring_boot_study.domains.order.service.OrderService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/api")
class OrderController(
    private val orderService: OrderService
) {

    @GetMapping
    fun getManyOrder(ordererId: Long): ResponseEntity<List<GetOrderResponse>> =
        ResponseEntity.ok(orderService.getManyOrder(ordererId).map { GetOrderResponse.from(it) })

    @PostMapping
    fun createOrder(ordererId: Long, @RequestBody @Valid request: CreateOrderRequest): ResponseEntity<Long> {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request.toCommand(ordererId)))
    }
}