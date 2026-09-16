package com.knowave.spring_boot_study.domains.order.service.dto

import com.knowave.spring_boot_study.domains.order.entity.Order
import com.knowave.spring_boot_study.domains.order.entity.OrderItem
import com.knowave.spring_boot_study.domains.order.entity.OrderStatus
import java.math.BigDecimal

data class GetOrderResult(
    val id: Long,
    val ordererId: Long,
    val status: OrderStatus,
    val orderedAt: java.time.Instant,
    val totalAmount: BigDecimal,
    val items: List<GetOrderItemResult>,
) {
    companion object {
        fun from(order: Order) = GetOrderResult(
            id = checkNotNull(order.id) { "영속화되지 않은 Order는 변환할 수 없습니다" },
            ordererId = order.ordererId,
            status = order.status,
            orderedAt = order.orderedAt,
            totalAmount = order.totalAmount,
            items = order.items.map { GetOrderItemResult.from(it) },
        )
    }
}

data class GetOrderItemResult(
    val id: Long,
    val productCode: String,
    val quantity: Int,
    val unitPrice: BigDecimal,
    val lineTotal: BigDecimal,
) {
    companion object {
        fun from(item: OrderItem) = GetOrderItemResult(
            id = checkNotNull(item.id) { "영속화되지 않은 OrderItem은 변환할 수 없습니다" },
            productCode = item.productCode,
            quantity = item.quantity,
            unitPrice = item.unitPrice,
            lineTotal = item.lineTotal,
        )
    }
}