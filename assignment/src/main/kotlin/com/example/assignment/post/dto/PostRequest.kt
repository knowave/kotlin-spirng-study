package com.example.assignment.post.dto

import jakarta.validation.constraints.NotBlank

data class PostRequest(
    @field:NotBlank(message = "제목은 필수 입력 사항입니다.")
    val title: String,

    @field:NotBlank(message = "작성자는 필수 입력 사항입니다.")
    val writer: String,

    @field:NotBlank(message = "내용은 필수 입력 사항입니다.")
    val content: String,

    @field:NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    val password: String,
)