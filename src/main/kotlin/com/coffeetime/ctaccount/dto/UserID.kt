package com.coffeetime.ctaccount.dto

data class UserIDReq(
    val socialID: String,
    val userID: Int? = null
)

data class UserIDRes(
    val socialID: String?,
    val userID: Int? = null
)