package com.coffeetime.ctaccount.infrastructure.persistent.rdbms


import com.coffeetime.ctaccount.infrastructure.entity.UserIDInfo
import org.springframework.data.repository.CrudRepository

interface UserIDRepository : CrudRepository<UserIDInfo, Long> {
    fun findBySocialID(socialID: String): UserIDInfo?
    fun findByUserID(userID: Int): Iterable<UserIDInfo>?
}