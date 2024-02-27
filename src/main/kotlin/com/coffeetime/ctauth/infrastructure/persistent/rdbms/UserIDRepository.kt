package com.coffeetime.ctauth.infrastructure.persistent.rdbms


import com.coffeetime.ctauth.infrastructure.entity.UserIDInfo
import org.springframework.data.repository.CrudRepository

interface UserIDRepository : CrudRepository<UserIDInfo, Long> {
    fun findBySocialID(socialID: String): UserIDInfo?
    fun findByUserID(userID: Int): Iterable<UserIDInfo>?
}