package com.coffeetime.ctaccount.domain.service

import com.coffeetime.ctaccount.infrastructure.entity.UserIDInfo
import com.coffeetime.ctaccount.infrastructure.persistent.rdbms.UserIDRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserIDServiceImpl : UserIDService {
    @Autowired
    private lateinit var repository: UserIDRepository

    override fun saveOrUpdateUserID(userID: UserIDInfo): UserIDInfo {
        return repository.save(userID)
    }

    override fun findBySocialID(socialID: String): UserIDInfo? {
        return repository.findBySocialID(socialID)
    }

    override fun findByUserID(userID: Int): Iterable<UserIDInfo>? {
        return repository.findByUserID(userID)
    }

    override fun findAll(): Iterable<UserIDInfo> {
        return repository.findAll()
    }
}