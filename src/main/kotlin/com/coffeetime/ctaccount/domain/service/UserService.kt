package com.coffeetime.ctaccount.domain.service

import com.coffeetime.ctaccount.infrastructure.entity.UserInfo

interface UserService {
    fun saveOrUpdateUser(user: UserInfo): UserInfo
    fun findByUserID(userID: Int): UserInfo?
    fun findBySocialID(socialID: String): UserInfo?
    fun findAll(): Iterable<UserInfo>
}