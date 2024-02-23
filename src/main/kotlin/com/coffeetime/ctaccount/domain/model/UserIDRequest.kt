package com.coffeetime.ctaccount.domain.model

data class UserIDRequest(
    val socialID: String,
    val userID: Int? = null
)