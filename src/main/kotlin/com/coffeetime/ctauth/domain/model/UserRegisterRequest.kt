package com.coffeetime.ctauth.domain.model

data class UserRegisterRequest(
    val nickName: String,
    val socialService: String,
    val socialID: String,
    val profilePictureURL: String? = null,
    val jobs: Iterable<String>,
)