package com.knowave.spring_boot_study.domains.order.repository

import com.knowave.spring_boot_study.domains.order.entity.Order
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = ["_items"])
    fun findAllByOrdererId(ordererId: Long): List<Order>
}