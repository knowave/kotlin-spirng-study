package com.example.coffee_order.domains.user.dtos

import jakarta.validation.constraints.*


data class UserPointResponse(
    val id: Long,
    val point: Long
)

data class UserPointRequestDto(
    @field:NotNull
    @field:Positive(message = "충전 금액은 0보다 커야합니다.")
    val point: Long
)