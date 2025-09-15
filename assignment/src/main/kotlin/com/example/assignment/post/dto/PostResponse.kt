package com.example.assignment.post.dto

import com.example.assignment.post.entity.Post

data class PostResponse (
    val id: Long,
    val writer: String,
    val title: String,
    val content: String
) {
    companion object {
        fun from(post: Post): PostResponse =
            PostResponse(
                id = post.id!!,
                writer = post.writer,
                title = post.title,
                content = post.content
            )
    }
}