package com.coffeetime.ctaccount.app.v1

import com.coffeetime.ctaccount.domain.model.UserRegisterRequest
import com.coffeetime.ctaccount.domain.service.UserIDService
import com.coffeetime.ctaccount.domain.service.UserService
import com.coffeetime.ctaccount.infrastructure.entity.UserIDInfo
import com.coffeetime.ctaccount.infrastructure.entity.UserInfo
import jakarta.persistence.LockModeType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Lock
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(@Autowired private val userService: UserService, @Autowired private val userIDService: UserIDService) {
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

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @PostMapping("/register")
    fun register(@RequestBody userReq: UserRegisterRequest): ResponseEntity<Int> {
        Thread.sleep(Math.random().toLong() * 300L + 100)
        // socialID 형식 검증 (서비스@아이디@도메인)
        val socialID = userReq.socialID
        if (!socialID.matches(Regex("^[a-z]+@[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$")))
            return ResponseEntity.badRequest().build()

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
        // TODO: 그냥 UserIDService, UserIDInfo 삭제하고 UserService, UserInfo로 통합
        //  => Service단에서 두 Entity에 대한 처리를 하도록 변경 (DB 트랜잭션 처리)
        val savedID = userService.saveOrUpdateUser(user).userID
        val userID = UserIDInfo(
            socialID = userReq.socialID,
            userID = savedID
        )
        userIDService.saveOrUpdateUserID(userID)
        return ResponseEntity.ok(savedID)
    }
}