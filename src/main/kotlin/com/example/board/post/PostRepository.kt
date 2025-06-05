package com.example.board.post.repository

import com.example.board.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
    fun findByAll(): List<Post>
}