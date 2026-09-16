package com.knowave.spring_boot_study.domains.order.controller.dto

import com.knowave.spring_boot_study.domains.order.service.dto.CreateOrderItemCommand
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

class CreateOrderItemRequest(
    @field:NotBlank(message = "상품 코드는 필수입니다")
    val productCode: String,

    @field:Min(1, message = "수량은 1개 이상이어야 합니다")
    val quantity: Int,

    @field:DecimalMin("0.0", inclusive = false, message = "가격은 0보다 커야 합니다")
    val unitPrice: BigDecimal,
) {
    fun toCommand() = CreateOrderItemCommand(productCode, quantity, unitPrice)
}