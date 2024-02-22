package com.coffeetime.ctaccount.service

import com.coffeetime.ctaccount.dao.UserRepository
import com.coffeetime.ctaccount.model.User
import org.springframework.beans.factory.annotation.Autowired

class UserService {
    @Autowired
    private lateinit var repository: UserRepository

    fun saveOrUpdateUser(user: User): User {
        return repository.save(user)
    }
    fun findByUserIDOrSocialID(userID: Int?, socialID: String?): User {
        if (userID != null)
            return repository.findByUserID(userID)
                .orElseThrow{ NoSuchElementException("No user found with socialID: $userID") }
        else if (socialID != null)
            return repository.findBySocialID(socialID)
                .orElseThrow{ NoSuchElementException("No user found with socialID: $socialID") }
        else
            throw IllegalArgumentException("one of [userID, socialID] must be given.")
    }
    fun findAll(): Iterable<User> {
        return repository.findAll()
    }
}