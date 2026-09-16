package com.knowave.spring_boot_study.domains.order.controller.dto

import com.knowave.spring_boot_study.domains.order.service.dto.CreateOrderCommand
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty

class CreateOrderRequest(
    @field:NotEmpty(message = "주문 항목은 최소 1개 이상이어야 합니다")
    @field:Valid
    val items: List<CreateOrderItemRequest>,
) {
    fun toCommand(ordererId: Long) = CreateOrderCommand(
        ordererId = ordererId,
        items = items.map { it.toCommand() },
    )
}