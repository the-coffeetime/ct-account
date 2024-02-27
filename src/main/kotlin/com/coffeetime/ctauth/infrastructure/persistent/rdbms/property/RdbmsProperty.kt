package com.coffeetime.ctauth.infrastructure.persistent.rdbms.property


data class RdbmsProperty (
    val url: String,
    val username: String,
    val password: String,
    val driverClassName: String
)