package com.example.assignment.post

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestHeader
import com.example.assignment.post.dto.*

@RestController
class PostController(
    private val postService: PostService
) {

    @PostMapping
    fun savePost(@RequestBody dto: PostRequest): Boolean {
        return postService.savePost(dto)
    }

    @GetMapping
    fun getAllPost(): List<PostResponse> {
        return postService.getAllPost()
    }
    
    @GetMapping("/{id}")
    fun getPost(@PathVariable id: Long): PostResponse {
        return postService.getPost(id)
    }
    
    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: Long, @RequestBody dto: PostRequest): Boolean {
        return postService.updatePost(id, dto)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long, @RequestBody dto: PostRequest): Boolean {
        return postService.deletePost(id, dto.password)
    }
}