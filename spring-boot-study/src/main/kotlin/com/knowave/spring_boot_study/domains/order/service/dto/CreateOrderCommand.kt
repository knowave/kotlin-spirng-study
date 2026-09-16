package com.knowave.spring_boot_study.domains.order.service.dto

data class CreateOrderCommand(
    val ordererId: Long,
    val items: List<CreateOrderItemCommand>
)