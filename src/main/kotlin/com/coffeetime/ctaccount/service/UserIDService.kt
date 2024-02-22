package com.coffeetime.ctaccount.service

import com.coffeetime.ctaccount.dao.UserIDRepository
import com.coffeetime.ctaccount.model.UserID
import org.springframework.beans.factory.annotation.Autowired

class UserIDService {
    @Autowired
    private lateinit var repository: UserIDRepository

    fun saveOrUpdateUserID(userID: UserID): UserID {
        return repository.save(userID)
    }
    fun findBySocialID(socialID: String): UserID? {
        return repository.findBySocialID(socialID)
    }
    fun findByUserID(userID: Int): Iterable<UserID>? {
        return repository.findByUserID(userID)
    }
    fun findAll(): Iterable<UserID> {
        return repository.findAll()
    }
}