package com.knowave.spring_boot_study.domains.order.service

import com.knowave.spring_boot_study.domains.order.entity.Order
import com.knowave.spring_boot_study.domains.order.repository.OrderJpaRepository
import com.knowave.spring_boot_study.domains.order.service.dto.CreateOrderCommand
import com.knowave.spring_boot_study.domains.order.service.dto.GetOrderResult
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
    private val orderRepository: OrderJpaRepository
) : OrderService {

    @Transactional
    override fun createOrder(command: CreateOrderCommand): Long {
        val order = Order.create(command.ordererId, command.items.map { it.toEntity() });
        return orderRepository.save(order).id!!
    }

    override fun getManyOrder(ordererId: Long): List<GetOrderResult> =
    orderRepository.findAllByOrdererId(ordererId)
    .map { GetOrderResult.from(it) }

}