package com.example.assignment.post.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import java.time.LocalDateTime

@Entity
@Table(name = "post")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "title", length = 250, nullable = false)
    var title: String,

    @Column(name = "writer", length = 50, nullable = false)
    var writer: String,

    @Column(name = "content", length = 500, nullable = false)
    var content: String,

    @Column(name = "password", length = 250, nullable = false)
    var password: String,

    @Column(name = "created_at", nullable = false, insertable = true, updatable = false)
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime? = LocalDateTime.now(),
)