package com.coffeetime.ctaccount.app.v1

import com.coffeetime.ctaccount.domain.model.UserRegisterRequest
import com.coffeetime.ctaccount.domain.service.UserService
import com.coffeetime.ctaccount.infrastructure.entity.UserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(@Autowired private val userService: UserService) {

    @GetMapping
    fun findUser(
        @RequestParam(required = false) userID: Int?,
        @RequestParam(required = false) socialID: String? = null
    ): ResponseEntity<Any> {
        return when {
            userID != null -> ResponseEntity.ok(userService.findByUserID(userID))
            socialID != null -> ResponseEntity.ok(userService.findBySocialID(socialID))
            else -> ResponseEntity.ok(userService.findAll())
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody userReq: UserRegisterRequest): ResponseEntity<Int> {
        val jobs:MutableList<String?> = userReq.jobs.toMutableList()
        for (i in 1 .. (4 - jobs.size))
            jobs.add(null)
        val user = UserInfo(
            nickName = userReq.nickName,
            profilePictureURL = userReq.profilePictureURL,
            job1 = jobs[0],
            job2 = jobs[1],
            job3 = jobs[2],
            job4 = jobs[3],
        )
        val savedID = userService.saveOrUpdateUser(user).userID
        return ResponseEntity.ok(savedID)
    }
}