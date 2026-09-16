package com.knowave.spring_boot_study.domains.order.enums

enum class OrderStatus(val label: String) {
    CREATED("주문 완료"),
    PAID("결제 완료"),
    CANCELLED("주문 취소"),
}