package com.coffeetime.ctaccount.controller

import com.coffeetime.ctaccount.dto.UserIDReq
import com.coffeetime.ctaccount.dto.UserIDRes
import com.coffeetime.ctaccount.model.UserID
import com.coffeetime.ctaccount.service.UserIDService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v0/userid")
class UserIDController(@Autowired private val userIDService: UserIDService) {

    @GetMapping
    fun findBySocialID(@RequestParam socialID: String) =
        userIDService.findBySocialID(socialID) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    @GetMapping
    fun findByUserID(@RequestParam userID: Int) =
        userIDService.findByUserID(userID) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    @GetMapping
    fun findAll() = userIDService.findAll()

    @PostMapping("/register")
    fun register(@RequestBody userIDReq: UserIDReq): ResponseEntity<UserIDRes> {
        val userID = UserID(
            socialID = userIDReq.socialID,
            userID = userIDReq.userID
        )
        val savedID = userIDService.saveOrUpdateUserID(userID)
        return ResponseEntity(UserIDRes(savedID.socialID, savedID.userID), HttpStatus.CREATED)
    }
}