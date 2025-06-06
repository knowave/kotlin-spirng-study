package com.example.kotlin_spring_security.member.repository

import com.example.kotlin_spring_security.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByLoginId(loginId: String): Member?
}