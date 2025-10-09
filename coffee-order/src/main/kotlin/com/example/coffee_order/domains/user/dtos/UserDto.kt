package com.example.coffee_order.domains.user.dtos

import jakarta.validation.constraints.*


data class UserPointResponse(
    val id: Long,
    val point: Long
)

data class UserPointRequestDto(
    @field:NotNull
    val point: Long
)