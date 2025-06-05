package com.example.board.post.dto

import jakarta.validation.constraints.NotBlank

data class CreatePostDto(
    @field:NotBlank
    val title: String,

    @field:NotBlank
    val content: String
)