package com.example.coffee_order.domains.menu

import com.example.coffee_order.domains.menu.entities.Menu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository : JpaRepository<Menu, Long> {
    // 인기 주문 메뉴 1위 ~ 10위
    @Query("SELECT menu FROM Menu menu ORDER BY menu.orderCount DESC LIMIT 10")
    fun findTop10ByOrderByOrderCountDesc(): List<Menu>

    fun findAllByIsActiveTrue(): List<Menu>
}