package com.example.coffee_order.common.entities

import jakarta.persistence.EntityListeners
import jakarta.persistence.*
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false,)
    open var createdAt: LocalDate? = LocalDate.now()

    @CreatedDate
    @Column(name = "updated_at", nullable = false, updatable = true,)
    open var updatedAt: LocalDate? = LocalDate.now()
}
