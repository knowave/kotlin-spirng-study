package com.example.coffee_order.domains.menu

import com.example.coffee_order.domains.menu.dtos.MenuResponse

class MenuService(
    private val menuRepository: MenuRepository
) {
    fun getManyMenu(): List<MenuResponse> =
        menuRepository.findAllByIsActiveTrue()
            .map { MenuResponse(it.id!!, it.name, it.price, it.orderCount) }
}