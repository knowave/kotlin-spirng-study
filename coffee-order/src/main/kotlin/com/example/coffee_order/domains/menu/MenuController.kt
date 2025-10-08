package com.example.coffee_order.domains.menu

import com.example.coffee_order.domains.menu.dtos.MenuResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/menus")
class MenuController(
    private val menuService: MenuService
) {

    @GetMapping
    fun getManyMenu(): ResponseEntity<List<MenuResponse>> {
        val menus = menuService.getManyMenu()
        return ResponseEntity.status(HttpStatus.OK).body(menus)
    }
}