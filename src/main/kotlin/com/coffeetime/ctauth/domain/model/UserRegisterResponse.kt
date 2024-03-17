package com.coffeetime.ctauth.domain.model

data class UserRegisterResponse (
    var userID: Int
) {
    fun userID(userID: Int): UserRegisterResponse {
        this.userID = userID
        return this
    }
}