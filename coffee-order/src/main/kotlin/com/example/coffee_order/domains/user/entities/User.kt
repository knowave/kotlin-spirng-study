package com.example.coffee_order.domains.user.entities

import com.example.coffee_order.common.entities.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
open class User(
    @Column(name = "username", unique = true)
    open var username: String,

    @Column(name = "email", unique = true)
    open var email: String,

    @Column(name = "password", length = 200)
    open var password: String,

    @Column(name = "refresh_token", length = 255)
    open var refreshToken: String,

    @Column(name = "point", nullable = false)
    open var point: Long = 0L
) : BaseEntity()