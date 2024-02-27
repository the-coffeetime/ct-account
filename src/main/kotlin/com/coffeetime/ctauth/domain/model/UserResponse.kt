package com.coffeetime.ctauth.domain.model

import java.time.Instant

data class UserResponse(
    val userID: Int,
    val createdAt: Instant,
    val lastLogin: Instant,
    val nickName: String,
    val email: String? = null,
    val profilePictureURL: String? = null,
    val verified: Boolean,
    val reports: Int,
    val jobs: List<String>,
    val company: String? = null,
    val introduction: String? = null,
    val status: String,
)
