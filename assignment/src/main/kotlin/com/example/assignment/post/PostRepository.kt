package com.example.assignment.post

import org.springframework.data.jpa.repository.JpaRepository
import com.example.assignment.post.entity.Post

interface PostRepository : JpaRepository<Post, Long> {}