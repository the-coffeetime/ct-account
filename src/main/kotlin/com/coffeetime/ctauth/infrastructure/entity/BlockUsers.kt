package com.coffeetime.ctauth.infrastructure.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "blockUsers")
class BlockUsers (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val userID: Long,

    @Column(nullable = false)
    val blockedUserID: Long,

    @Column(nullable = false)
    val isReported: Boolean = false,

    @Column(nullable = false)
    val createdAt: Instant = Instant.now()
)