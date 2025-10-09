package com.example.coffee_order.domains.order

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import com.example.coffee_order.domains.order.dtos.CreateOrderRequestDto
import com.example.coffee_order.domains.order.dtos.CreateOrderResponseDto
import com.example.coffee_order.domains.order.entities.Order
import com.example.coffee_order.domains.order.entities.OrderStatus
import com.example.coffee_order.domains.user.UserRepository
import com.example.coffee_order.domains.user.entities.User
import com.example.coffee_order.domains.menu.MenuRepository
import com.example.coffee_order.domains.menu.entities.Menu

@Service
class OrderService(
    private val userRepository: UserRepository,
    private val menuRepository: MenuRepository,
    private val orderRepository: OrderRepository
) {

    private fun validateOrder(orderId: Long): Order {
        val order = orderRepository.findById(orderId)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found") }

        return order
    }

    private fun validateUser(userId: Long): User {
        val user = userRepository.findById(userId)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "User not found") }

        return user
    }

    private fun validateMenu(menuId: Long): Menu {
        val menu = menuRepository.findById(menuId)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found") }

        return menu
    }
    
    @Transactional
    fun createOrder(userId: Long, dto: CreateOrderRequestDto): CreateOrderResponseDto {
        val user = validateUser(userId)
        val menu = validateMenu(dto.menuId)

        if (user.point < menu.price) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User point is not enough")
        }

        user.point -= menu.price
        menu.orderCount += 1

        val order = orderRepository.save(
            Order(
                user = user,
                menu = menu,
                price = menu.price,
                status = OrderStatus.COMPLETED
            )
        )

        return CreateOrderResponseDto(
            id = order.id!!,
            menu = menu.name,
            user = user.username,
            price = menu.price,
            status = order.status
        )
    }

    @Transactional
    fun cancelOrder(userId: Long, orderId: Long) {
        val order = validateOrder(orderId)
        order.user.id!! == userId || throw ResponseStatusException(HttpStatus.FORBIDDEN, "User is not authorized to cancel this order")

        order.status = OrderStatus.CANCELLED
        order.user.point += order.price
        order.menu.orderCount -= 1

        orderRepository.save(order)
        menuRepository.save(order.menu)
        userRepository.save(order.user)
    }
}