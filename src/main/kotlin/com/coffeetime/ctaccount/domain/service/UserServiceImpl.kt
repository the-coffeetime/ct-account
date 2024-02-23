package com.coffeetime.ctaccount.domain.service

import com.coffeetime.ctaccount.infrastructure.entity.UserInfo
import com.coffeetime.ctaccount.infrastructure.persistent.rdbms.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    @Autowired
    private lateinit var repository: UserRepository

    override fun saveOrUpdateUser(user: UserInfo): UserInfo {
        return repository.save(user)
    }
    override fun findByUserID(userID: Int): UserInfo? =
        repository.findByUserID(userID)
    override fun findBySocialID(socialID: String): UserInfo? =
        repository.findBySocialID(socialID)

    override fun findAll(): Iterable<UserInfo> {
        return repository.findAll()
    }
}