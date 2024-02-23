package com.coffeetime.ctaccount.domain.service

import com.coffeetime.ctaccount.infrastructure.entity.UserIDInfo

interface UserIDService {
    fun saveOrUpdateUserID(userID: UserIDInfo): UserIDInfo
    fun findBySocialID(socialID: String): UserIDInfo?
    fun findByUserID(userID: Int): Iterable<UserIDInfo>?
    fun findAll(): Iterable<UserIDInfo>
}