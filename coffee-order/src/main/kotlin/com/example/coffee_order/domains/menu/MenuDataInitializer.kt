package com.example.coffee_order.domains.menu

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import org.springframework.boot.CommandLineRunner
import com.example.coffee_order.domains.menu.entities.Menu


@Configuration
class MenuDataInitializer {

    @Bean
    fun menuDataInitializerRunner(menuRepository: MenuRepository) = CommandLineRunner {
        // 데이터베이스에 메뉴가 없으면 초기 데이터 추가
        if (menuRepository.count() == 0L) {
            menuRepository.saveAll(
                listOf(
                    Menu( name = "아메리카노", price = 4500, isActive = true, orderCount = 0 ),
                    Menu( name = "카페라떼", price = 5000, isActive = true, orderCount = 0 ),
                    Menu( name = "카푸치노", price = 5500, isActive = true, orderCount = 0 ),
                    Menu( name = "카라멜마키야또", price = 6000, isActive = true, orderCount = 0 ),
                    Menu( name = "모카", price = 6500, isActive = true, orderCount = 0 ),
                    Menu( name = "바닐라라떼", price = 7000, isActive = true, orderCount = 0 ),
                    Menu( name = "오트라떼", price = 7500, isActive = true, orderCount = 0 ),
                    Menu( name = "카페모카", price = 8000, isActive = true, orderCount = 0 ),
                )
            )
        }
    }
}