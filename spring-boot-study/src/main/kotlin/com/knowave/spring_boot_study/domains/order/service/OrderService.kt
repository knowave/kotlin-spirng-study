package com.knowave.spring_boot_study.domains.order.service

import com.knowave.spring_boot_study.domains.order.service.dto.CreateOrderCommand
import com.knowave.spring_boot_study.domains.order.service.dto.GetOrderResult

interface OrderService {
    fun createOrder(command: CreateOrderCommand): Long
    fun getManyOrder(ordererId: Long): List<GetOrderResult>
}