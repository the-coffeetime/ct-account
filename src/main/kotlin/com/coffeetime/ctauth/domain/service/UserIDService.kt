package com.coffeetime.ctauth.domain.service

import com.coffeetime.ctauth.infrastructure.entity.UserIDInfo

interface UserIDService {
    fun saveOrUpdateUserID(userID: UserIDInfo): UserIDInfo
    fun findBySocialID(socialID: String): UserIDInfo?
    fun findByUserID(userID: Int): Iterable<UserIDInfo>?
    fun findAll(): Iterable<UserIDInfo>
}