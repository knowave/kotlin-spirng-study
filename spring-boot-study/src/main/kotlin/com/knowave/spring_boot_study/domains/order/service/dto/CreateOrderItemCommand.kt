package com.knowave.spring_boot_study.domains.order.service.dto

import com.knowave.spring_boot_study.domains.order.entity.OrderItem
import java.math.BigDecimal

data class CreateOrderItemCommand(
    val productCode: String,
    val quantity: Int,
    val unitPrice: BigDecimal,
) {
    fun toEntity() = OrderItem(productCode, quantity, unitPrice)
}
