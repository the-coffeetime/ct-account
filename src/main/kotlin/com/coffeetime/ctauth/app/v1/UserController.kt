package com.coffeetime.ctauth.app.v1

import com.coffeetime.ctauth.common.property.google
import com.coffeetime.ctauth.common.property.kakao
import com.coffeetime.ctauth.common.property.naver
import com.coffeetime.ctauth.domain.model.UserRegisterRequest
import com.coffeetime.ctauth.domain.service.UserService
import com.coffeetime.ctauth.infrastructure.entity.UserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(@Autowired private val userService: UserService) {
    @GetMapping
    fun findUser(
        @RequestParam(required = false) userID: Int?,
        @RequestParam(required = false) socialID: String? = null,
        @RequestParam(required = false) service: String? = null
    ): ResponseEntity<Any> {
        return when {
            userID != null -> {
                if (userID <= 0 || socialID != null || service != null)
                    ResponseEntity.badRequest().body("Invalid request")
                else
                    ResponseEntity.ok(userService.findByUserID(userID))
            }
            socialID != null -> {
                when (service) {
                    google, naver, kakao -> ResponseEntity.ok(userService.findBySocialID(socialID, service))
                    else -> ResponseEntity.badRequest().body("Invalid social service")
                }
            }
            else -> ResponseEntity.badRequest().body("Invalid request")
        }
    }

    @GetMapping("/all")
    fun findAll(): ResponseEntity<Iterable<UserInfo>> {
        return ResponseEntity.ok(userService.findAll())
    }


    @PostMapping("/register")
    fun register(@RequestBody userReq: UserRegisterRequest): ResponseEntity<Int> {
        Thread.sleep(Math.random().toLong() * 300L + 100)
        val socialID = userReq.socialID
        val socialService = userReq.socialService
        if (socialID.isBlank() || socialService.isBlank())
            return ResponseEntity.badRequest().body(-1)
        if (userService.findBySocialID(socialID, socialService) != null)
            return ResponseEntity.badRequest().body(-2)
        if (socialService != google && socialService != naver && socialService != kakao)
            return ResponseEntity.badRequest().body(-3)

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
        when (socialService) {
            google -> user.socialIDGoogle = socialID
            naver -> user.socialIDNaver = socialID
            kakao -> user.socialIDKakao = socialID
        }

        val savedID = userService.saveOrUpdateUser(user).userID

        return ResponseEntity.ok(savedID)
    }
}