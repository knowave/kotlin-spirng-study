package com.example.board.post.dto

import jakarta.validation.constraints.NotBlank

data class PostRequest(
    @field:NotBlank
    val title: String,

    @field:NotBlank
    val content: String
)