package com.coffeetime.ctaccount.domain.model

data class UserRegisterRequest(
    val nickName: String,
    val profilePictureURL: String? = null,
    val jobs: Iterable<String>,
)