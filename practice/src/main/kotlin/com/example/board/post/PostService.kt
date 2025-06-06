package com.example.board.post

import com.example.board.post.dto.PostRequest
import com.example.board.post.dto.PostResponse
import com.example.board.post.entity.Post
import com.example.board.post.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService (
    private val postRepository: PostRepository
) {

    fun createPost(dto: PostRequest): Long {
        val post = Post(
            title = dto.title,
            content = dto.content
        )

        return postRepository.save(post).id
    }

    fun getPost(id: Long): PostResponse {
        val post = postRepository.findById(id)
            .orElseThrow()

        return PostResponse.from(post)
    }

    fun updatePost(id: Long, dto: PostRequest): PostResponse {
        val post = postRepository.findById(id)
            .orElseThrow()

        val updatedPost = post.copy(
            title = dto.title,
            content = dto.content
        )

        return PostResponse.from(updatedPost)
    }

    fun deletePost(id: Long): Boolean {
        if (!postRepository.existsById(id)) {
            throw NoSuchElementException("Post Not Found")
        }

        postRepository.deleteById(id)
        return true
    }
}