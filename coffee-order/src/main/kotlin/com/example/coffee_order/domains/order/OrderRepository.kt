package com.example.coffee_order.domains.order

import org.springframework.data.jpa.repository.JpaRepository
import com.example.coffee_order.domains.order.entities.Order

interface OrderRepository: JpaRepository<Order, Long> {}