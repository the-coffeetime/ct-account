package com.coffeetime.ctauth.domain.service

import com.coffeetime.ctauth.infrastructure.entity.UserInfo

interface UserService {
    fun saveOrUpdateUser(user: UserInfo): UserInfo
    fun findByUserID(userID: Int): UserInfo?
    fun findBySocialID(socialID: String, socialService: String): UserInfo?
    fun findAll(): Iterable<UserInfo>
}