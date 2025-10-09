package com.example.coffee_order.domains.order.dtos

import org.jetbrains.annotations.NotNull
import com.example.coffee_order.domains.order.entities.OrderStatus

data class CreateOrderRequestDto(
    @field:NotNull
    val menuId: Long,
)

data class CreateOrderResponseDto(
    val id: Long,
    val menu: String,
    val user: String,
    val price: Int,
    val status: OrderStatus
)