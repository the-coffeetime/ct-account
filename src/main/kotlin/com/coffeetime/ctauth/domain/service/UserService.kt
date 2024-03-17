package com.coffeetime.ctauth.domain.service

import com.coffeetime.ctauth.domain.model.UserRegisterRequest
import com.coffeetime.ctauth.domain.model.UserRegisterResponse
import com.coffeetime.ctauth.domain.model.UserResponse
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun saveOrUpdateUser(userReq: UserRegisterRequest): UserRegisterResponse
    fun findByUserID(userID: Long): UserResponse?
    fun findBySocialID(socialID: String, socialService: String): UserResponse?
    fun findAll(): Iterable<UserResponse>
}