package com.knowave.spring_boot_study.domains.order.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal

/**
 * 주문 항목의 상품 코드나 수량은 바뀌지 않아서 var대신 val로 선언.
 * 가격은 BigDecimal을 사용햇다. 그 이유는 돈에 대한 금액에 Double을 사용하게 되면
 * 0.1 + 0.2 != 0.3이 되는 부동소수점 오차가 금액 합계에서 그대로 드러난다.
 * Kotlin은 BigDecimal에 연산자 오버로딩이 있어서 unitPrice * quantity처럼 그냥 곱해도 된다.
 * lateinit var order는 양방향의 순환 문제를 풀 수 있는 방법이다.
 * OrderItem을 만들기 위해 Order가 필요하고, Order를 만들기 위 OrderItem이 필요한 닭과 계란같은 관계다.
 * Order?로 두게 된다면 모든 코드에서 null check를 해야되는 귀찮음이 존재한다.
 * lateinit은 생성 직후에는 비어있지만 정상 사용 시점에는 반드시 채워져있다.
 * 따라서 Order는 non null이 된다.
 * 초기화 전에 접근하면 명확한 메시지의 UninitializedPropertyAccessException이 난다.
 * internal set은 같은 모듈 안에서만 대입 가능하게 막는 거다. Order.addItem()이 채워주고, 외부에서는 바꿀 수 없다.
 */
@Entity
@Table(name = "order_items")
class OrderItem(
    @Column(nullable = false)
    val productCode: String,

    @Column(nullable = false)
    val quantity: Int,

    @Column(nullable = false)
    val unitPrice: BigDecimal,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    lateinit var order: Order
        internal set

    val lineTotal: BigDecimal
        get() = unitPrice * quantity.toBigDecimal()
}