package com.example.coffee_order.domains.user.entities

import com.example.coffee_order.common.entities.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Column(name = "username", unique = true)
    var username: String,

    @Column(name = "email", unique = true)
    var email: String,

    @Column(name = "password",  length = 200)
    var password: String,

    @Column(name = "refresh_token", length = 255)
    var refreshToken: String
) : BaseEntity()