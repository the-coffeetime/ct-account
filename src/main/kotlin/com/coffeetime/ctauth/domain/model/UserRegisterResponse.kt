package com.coffeetime.ctauth.domain.model

data class UserRegisterResponse (
    var userID: Long
) {
    fun userID(userID: Long): UserRegisterResponse {
        this.userID = userID
        return this
    }
}