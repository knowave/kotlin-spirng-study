package com.example.board.post

import com.example.board.post.dto.PostRequest
import com.example.board.post.dto.PostResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
    private val postService: PostService
) {

    @PostMapping
    fun createPost(@RequestBody @Valid request: PostRequest): ResponseEntity<Long> {
        val id = postService.createPost(request)
        return ResponseEntity.status(HttpStatus.CREATED).body<Long>(id)
    }

    @GetMapping("/{id}")
    fun getPost(@PathVariable id: Long): ResponseEntity<PostResponse> {
        val post = postService.getPost(id)
        return ResponseEntity.ok(post)
    }

    @PatchMapping("/{id}")
    fun updatePost(@PathVariable id: Long, @RequestBody @Valid request: PostRequest): ResponseEntity<PostResponse> {
        val post = postService.updatePost(id, request)
        return ResponseEntity.ok(post)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Boolean> {
        val isDelete = postService.deletePost(id)
        return ResponseEntity.ok(isDelete)
    }
}