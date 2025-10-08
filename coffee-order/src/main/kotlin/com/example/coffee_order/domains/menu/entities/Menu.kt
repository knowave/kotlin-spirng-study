package com.example.coffee_order.domains.menu.entities

import com.example.coffee_order.common.entities.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "menus")
class Menu(
    @Column(name = "name")
    var name: String,

    @Column(name = "price")
    var price: Int,

    @Column(name = "isActive")
    var isActive: Boolean,

    @Column(name = "order_count")
    var orderCount: Int
): BaseEntity()