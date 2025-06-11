package com.example.kotlin_spring_security.member

import com.example.kotlin_spring_security.common.exception.InvalidInputException
import com.example.kotlin_spring_security.member.dto.MemberDtoRequest
import com.example.kotlin_spring_security.member.entity.Member
import com.example.kotlin_spring_security.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {
    /**
     * 회원가입
     */
    fun signUp(memberDtoRequest: MemberDtoRequest): String {
        // ID 중복검사
        var member: Member? = memberRepository.findByLoginId(memberDtoRequest.loginId)

        if (member != null) {
            throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")
        }

        val encodePassword = passwordEncoder.encode(memberDtoRequest.password)
        member = memberDtoRequest.copy(_password = encodePassword).toEntity()
        memberRepository.save(member)

        return "회원가입이 완료되었습니다."
    }
}