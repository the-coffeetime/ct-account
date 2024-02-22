package com.coffeetime.ctaccount.dao


import com.coffeetime.ctaccount.model.UserID
import org.springframework.data.repository.CrudRepository

interface UserIDRepository : CrudRepository<UserID, Long> {
    fun findBySocialID(socialID: String): UserID?
    fun findByUserID(userID: Int): Iterable<UserID>?
}