package com.example.board.post.dto

import com.example.board.post.entity.Post

data class PostResponse (
    val id: Long,
    val title: String,
    val content: String
) {
    companion object {
        fun from(post: Post): PostResponse =
            PostResponse(
                id = post.id,
                title = post.title,
                content = post.content
            )
    }
}