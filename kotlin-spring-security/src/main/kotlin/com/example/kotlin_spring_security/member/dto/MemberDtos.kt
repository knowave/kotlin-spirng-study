package com.example.kotlin_spring_security.member.dto

import com.example.kotlin_spring_security.common.status.Gender
import java.time.LocalDate

data class MemberDtoRequest(
    val id: Long?,
    val loginId: String,
    val email: String,
    val password: String,
    val name: String,
    val birthDate: LocalDate,
    val gender: Gender,
)
