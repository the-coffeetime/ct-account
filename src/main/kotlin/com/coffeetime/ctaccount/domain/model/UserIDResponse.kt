package com.coffeetime.ctaccount.domain.model

data class UserIDResponse(
    val socialID: String?,
    val userID: Int? = null
)