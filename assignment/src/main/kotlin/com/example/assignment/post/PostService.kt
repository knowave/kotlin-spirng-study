package com.example.assignment.post

import org.springframework.stereotype.Service
import com.example.assignment.post.entity.Post
import com.example.assignment.post.dto.PostRequest

@Service
class PostService(
    private val postRepository: PostRepository
) {
    fun savePost(dto: PostRequest) {
        val post = Post(
            writer = dto.writer,
            title = dto.title,
            content = dto.content,
            password = dto.password
        )

        postRepository.save(post)
    }
}