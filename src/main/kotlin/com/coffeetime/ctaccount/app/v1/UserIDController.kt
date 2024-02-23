package com.coffeetime.ctaccount.app.v1

import com.coffeetime.ctaccount.domain.model.UserIDRequest
import com.coffeetime.ctaccount.domain.model.UserIDResponse
import com.coffeetime.ctaccount.domain.service.UserIDService
import com.coffeetime.ctaccount.infrastructure.entity.UserIDInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/userid")
class UserIDController(@Autowired private val userIDService: UserIDService) {

    @GetMapping
    fun findUserID(
        @RequestParam(required = false) userID: Int?,
        @RequestParam(required = false) socialID: String?
    ): ResponseEntity<Any> {
        return when {
            userID != null -> ResponseEntity.ok(userIDService.findByUserID(userID))
            socialID != null -> ResponseEntity.ok(userIDService.findBySocialID(socialID))
            else -> ResponseEntity.ok(userIDService.findAll())
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody userIDRequest: UserIDRequest): ResponseEntity<UserIDResponse> {
        val userID = UserIDInfo(
            socialID = userIDRequest.socialID,
            userID = userIDRequest.userID
        )
        val savedID = userIDService.saveOrUpdateUserID(userID)
        return ResponseEntity(UserIDResponse(savedID.socialID, savedID.userID), HttpStatus.CREATED)
    }
}