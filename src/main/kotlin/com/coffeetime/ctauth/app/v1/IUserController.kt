package com.coffeetime.ctauth.app.v1

import com.coffeetime.ctauth.domain.model.UserRegisterRequest
import com.coffeetime.ctauth.infrastructure.entity.UserInfo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

interface IUserController {
    @GetMapping
    fun findUser(
        @RequestParam(required = false) userID: Int?,
        @RequestParam(required = false) socialID: String? = null,
        @RequestParam(required = false) service: String? = null
    ): ResponseEntity<Any>

    @GetMapping("/all")
    fun findAllUsers(): ResponseEntity<Iterable<UserInfo>>

    @PostMapping("/register")
    fun registerUser(@RequestBody userReq: UserRegisterRequest): ResponseEntity<Int>
}