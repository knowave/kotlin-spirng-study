package com.knowave.spring_boot_study.domains.order.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.GeneratedColumn
import java.math.BigDecimal
import java.time.Instant

/**
 * data class는 모든 프로퍼티를 equals, hashCode, toString을 자동생성한다.
 * Entity는 값이 변하고, 연관관계는 지연 로딩이라 toString() 한 번에 전체 그래프를 끌고오거나
 * LazyInitializationException이 터진다. hashCode는 영속화 전후로 바뀌어서 Set을 넣으면 사라진다.
 * 따라서 data class보다 class를 쓰는 게 좋다.
 */
@Entity
@Table(name="orders")
class Order(
    @Column(nullable = false)
    val ordererId: Long,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column(nullable = false)
    var status: String = OrderStatus.CREATED.name
        protected set

    @Column(nullable = false)
    val orderedAt: Instant = Instant.now()

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val _items: MutableList<OrderItem> = mutableListOf()
    val items: List<OrderItem> get() = _items.toList()

    val totalAmount: BigDecimal
        get() = _items.sumOf { it.lineTotal }

    fun addItem(item: OrderItem) {
        _items += item
        item.order = this
    }

    fun cancel() {
        check(status == OrderStatus.CREATED.name) { "이미 처리된 주문은 취소할 수 없습니다" }
        status = OrderStatus.CANCELD.name
    }

    companion object {
        fun create(ordererId: Long, items: List<OrderItem>): Order {
            require(items.isNotEmpty()) { "주문 항목이 비어 있습니다" }
            return Order(ordererId).apply { items.forEach(::addItem) }
        }
    }
}