package com.knowave.spring_boot_study.domains.order

import com.knowave.spring_boot_study.domains.order.entity.Order
import com.knowave.spring_boot_study.domains.order.entity.OrderItem
import org.springframework.test.util.ReflectionTestUtils
import java.math.BigDecimal

fun createOrderItem(
    productCode: String = "TEST001",
    quantity: Int = 1,
    unitPrice: BigDecimal = BigDecimal("15000"),
    id: Long? = null,
): OrderItem = OrderItem(productCode, quantity, unitPrice)
    .apply { id?.let { setField( "id", it) } }

fun createOrder(
    ordererId: Long = 1L,
    items: List<OrderItem> = listOf(createOrderItem()),
    id: Long? = null,
): Order = Order.create(ordererId, items)
    .apply { id?.let { setField( "id", it) } }

/** save()가 DB처럼 id를 채워주는 걸 흉내낸다 */
fun Order.asPersisted(startId: Long = 1L): Order = apply {
    setField( "id", startId)
    items.forEachIndexed { i, item -> item.setField( "id", startId + i) }
}

fun Any.setField(name: String, value: Any?) =
    ReflectionTestUtils.setField(this, name, value)