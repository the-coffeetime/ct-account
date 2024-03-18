package com.coffeetime.ctauth.infrastructure.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "userInfo")
class UserInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userID: Long? = null,

    @Column(nullable = false)
    var createdAt: Instant = Instant.now(),

    @Column(nullable = false)
    var lastLogin: Instant = Instant.now(),

    @Column(nullable = false, length = 80)
    var nickName: String,

    @Column(length = 255)
    var socialIDGoogle: String? = null,

    @Column(length = 255)
    var socialIDNaver: String? = null,

    @Column(length = 255)
    var socialIDKakao: String? = null,

    @Column(unique = true, length = 255)
    var email: String? = null,

    @Column(length = 255)
    var profilePictureURL: String? = null,

    @Column(nullable = false)
    var verified: Boolean = false,

    @Column(length = 30)
    var job1: String? = null,

    @Column(length = 30)
    var job2: String? = null,

    @Column(length = 30)
    var job3: String? = null,

    @Column(length = 30)
    var job4: String? = null,

    @Column(length = 255)
    var company: String? = null,

    @Column(length = 255)
    var introduction: String? = null,

    @Column(length = 20)
    var status: String? = null
)