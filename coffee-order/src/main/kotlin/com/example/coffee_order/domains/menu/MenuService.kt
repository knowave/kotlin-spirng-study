package com.example.coffee_order.domains.menu

import com.example.coffee_order.domains.menu.dtos.MenuResponse
import org.springframework.stereotype.Service

@Service
class MenuService(
    private val menuRepository: MenuRepository
) {
    fun getManyMenu(): List<MenuResponse> =
        menuRepository.findAllByIsActiveTrue()
            .map { MenuResponse(it.id!!, it.name, it.price, it.orderCount) }
}