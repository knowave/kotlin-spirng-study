package com.knowave.spring_boot_study.domains.order.service

import com.knowave.spring_boot_study.domains.order.asPersisted
import com.knowave.spring_boot_study.domains.order.entity.Order
import com.knowave.spring_boot_study.domains.order.repository.OrderJpaRepository
import com.knowave.spring_boot_study.domains.order.service.dto.CreateOrderCommand
import com.knowave.spring_boot_study.domains.order.service.dto.CreateOrderItemCommand
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class OrderServiceUnitTest {

    private val orderRepository = mockk<OrderJpaRepository>()
    private val sut = OrderServiceImpl(orderRepository)

    @Test
    fun `주문 항목이 비어있으면 주문을 생성할 수 없다`() {
        val command = CreateOrderCommand(ordererId = 1L, items = emptyList())

        assertThrows<IllegalArgumentException>{ sut.createOrder(command) }

        verify(exactly = 0) { orderRepository.save(any()) }
    }

    @Test
    fun `주문 생성 성공 시 주문의 id를 반환한다`() {
        val command = CreateOrderCommand(ordererId = 1L,
            items = listOf(CreateOrderItemCommand( "SUCCESS-1001", 2, BigDecimal("15000"))),
            )

        every { orderRepository.save(any()) } answers { firstArg<Order>().asPersisted(startId = 100L) }

        val orderId = sut.createOrder(command)

        assertEquals(100L, orderId)
    }
}