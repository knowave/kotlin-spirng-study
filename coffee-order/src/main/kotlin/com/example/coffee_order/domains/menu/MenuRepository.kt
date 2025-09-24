package com.example.coffee_order.domains.menu

import com.example.coffee_order.domains.menu.entities.Menu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository : JpaRepository<Menu, Long>