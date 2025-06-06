package com.example.kotlin_spring_security.member.entity

import com.example.kotlin_spring_security.common.status.Gender
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(name = "uk_member_login_id", columnNames = ["loginId"])]
)
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    // loginId는 update 가 되지 않음.
    @Column(nullable = false, length = 30, updatable = false)
    val loginId: String,

    @Column(nullable = false, length = 30)
    val email: String,

    @Column(nullable = false, length = 100)
    val password: String,

    @Column(nullable = false, length = 10)
    val name: String,

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    val birthDate: LocalDate,

    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    val gender: Gender,
)