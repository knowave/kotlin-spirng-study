package com.example.coffee_order.domains.menu.dtos

data class MenuResponse(
    val id: Long,
    val name: String,
    val price: Int,
    val orderCount: Int
)