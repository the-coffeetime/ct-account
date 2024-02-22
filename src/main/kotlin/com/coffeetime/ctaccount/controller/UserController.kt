package com.coffeetime.ctaccount.controller

import com.coffeetime.ctaccount.dto.UserRegisterReq
import com.coffeetime.ctaccount.model.User
import com.coffeetime.ctaccount.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v0/userid")
class UserController(@Autowired private val userService: UserService) {

    @GetMapping
    fun findByUserID(@RequestParam userID: Int? = null, @RequestParam socialID: String? = null) =
        try {
            userService.findByUserIDOrSocialID(userID, socialID)
        } catch (e: NoSuchElementException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    @GetMapping
    fun findAll() = userService.findAll()

    @PostMapping("/register")
    fun register(@RequestBody userReq: UserRegisterReq): ResponseEntity<Int> {
        val jobs:MutableList<String?> = userReq.jobs.toMutableList()
        for (i in 1 .. (4 - jobs.size))
            jobs.add(null)
        val user = User(
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