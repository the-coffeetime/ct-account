package com.coffeetime.ctauth.domain.model

data class UserIDRequest(
    val socialID: String,
    val userID: Int? = null
)