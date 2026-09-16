package com.knowave.spring_boot_study.domains.order.controller.dto

import com.knowave.spring_boot_study.domains.order.entity.OrderStatus
import com.knowave.spring_boot_study.domains.order.service.dto.GetOrderItemResult
import com.knowave.spring_boot_study.domains.order.service.dto.GetOrderResult
import java.math.BigDecimal
import java.time.Instant

data class GetOrderResponse(
    val orderId: Long,
    val status: OrderStatus,
    val orderedAt: Instant,
    val totalAmount: BigDecimal,
    val items: List<GetOrderItemResponse>,
) {
    companion object {
        fun from(result: GetOrderResult) = GetOrderResponse(
            orderId = result.id,
            status = result.status,
            orderedAt = result.orderedAt,
            totalAmount = result.totalAmount,
            items = result.items.map { GetOrderItemResponse.from(it) },
        )
    }
}

data class GetOrderItemResponse(
    val productCode: String,
    val quantity: Int,
    val unitPrice: BigDecimal,
    val lineTotal: BigDecimal,
) {companion object {
    fun from(result: GetOrderItemResult) = GetOrderItemResponse(
        productCode = result.productCode,
        quantity = result.quantity,
        unitPrice = result.unitPrice,
        lineTotal = result.lineTotal,
    )
}}