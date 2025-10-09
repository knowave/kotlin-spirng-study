package com.example.coffee_order.domains.order.entities

import com.example.coffee_order.common.entities.BaseEntity
import com.example.coffee_order.domains.user.entities.User
import com.example.coffee_order.domains.menu.entities.Menu

import jakarta.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    var menu: Menu,

    @Column(name = "price")
    var price: Int,

    @Column(name = "status")
    var status: OrderStatus
): BaseEntity()

enum class OrderStatus {
    COMPLETED,
    CANCELLED
}