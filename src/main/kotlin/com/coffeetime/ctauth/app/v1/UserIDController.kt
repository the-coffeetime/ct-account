package com.coffeetime.ctauth.app.v1

import com.coffeetime.ctauth.domain.model.UserIDRequest
import com.coffeetime.ctauth.domain.model.UserIDResponse
import com.coffeetime.ctauth.domain.service.UserIDService
import com.coffeetime.ctauth.infrastructure.entity.UserIDInfo
import jakarta.persistence.LockModeType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Lock
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

    @Lock(LockModeType.PESSIMISTIC_WRITE)
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