package com.example.assignment.post

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import com.example.assignment.post.entity.Post
import com.example.assignment.post.dto.PostRequest
import com.example.assignment.post.dto.PostResponse

@Service
class PostService(
    private val postRepository: PostRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun savePost(dto: PostRequest): Boolean {
        val post = Post(
            writer = dto.writer,
            title = dto.title,
            content = dto.content,
            password = passwordEncoder.encode(dto.password)
        )

        postRepository.save(post)
        return true
    }

    fun getAllPost(): List<PostResponse> {
        return postRepository.findAll().map { PostResponse.from(it) }
    }

    fun getPost(id: Long): PostResponse {
        return PostResponse.from(postRepository.findById(id).orElseThrow { RuntimeException("Not Found Post") })
    }

    fun updatePost(id: Long, dto: PostRequest): Boolean {
        val post = postRepository.findById(id).orElseThrow { RuntimeException("Not Found Post") }
        post.title = dto.title
        post.content = dto.content
        post.password = passwordEncoder.encode(dto.password)
        postRepository.save(post)
        return true
    }
    
    fun deletePost(id: Long): Boolean {
        postRepository.deleteById(id)
        return true
    }
}