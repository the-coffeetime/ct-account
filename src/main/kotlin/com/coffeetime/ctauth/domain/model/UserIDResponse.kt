package com.coffeetime.ctauth.domain.model

data class UserIDResponse(
    val socialID: String?,
    val userID: Int? = null
)