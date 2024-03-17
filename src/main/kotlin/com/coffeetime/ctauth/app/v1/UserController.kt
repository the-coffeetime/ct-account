package com.coffeetime.ctauth.app.v1

import com.coffeetime.ctauth.common.property.google
import com.coffeetime.ctauth.common.property.kakao
import com.coffeetime.ctauth.common.property.naver
import com.coffeetime.ctauth.domain.model.UserRegisterRequest
import com.coffeetime.ctauth.domain.model.UserRegisterResponse
import com.coffeetime.ctauth.domain.model.UserResponse
import com.coffeetime.ctauth.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(@Autowired private val userService: UserService) : IUserController {
    @GetMapping
    override fun findUser(
        @RequestParam(required = false) userID: Long?,
        @RequestParam(required = false) socialID: String?,
        @RequestParam(required = false) service: String?
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
    override fun findAllUsers(): ResponseEntity<Iterable<UserResponse>> {
        return ResponseEntity.ok(userService.findAll())
    }


    @PostMapping("/register")
    override fun registerUser(@RequestBody userReq: UserRegisterRequest): ResponseEntity<UserRegisterResponse> {
        Thread.sleep(Math.random().toLong() * 300L + 100)
        val socialID = userReq.socialID
        val socialService = userReq.socialService

        if (socialID.isBlank() || socialService.isBlank())
            return ResponseEntity.badRequest().body(UserRegisterResponse(userID=-1))
        if (userService.findBySocialID(socialID, socialService) != null)
            return ResponseEntity.badRequest().body(UserRegisterResponse(userID=-2))
        if (socialService != google && socialService != naver && socialService != kakao)
            return ResponseEntity.badRequest().body(UserRegisterResponse(userID=-3))

        val userRegisterResponse = userService.saveOrUpdateUser(userReq)
        val savedID = userRegisterResponse.userID
        if (savedID <= 0)
            return ResponseEntity.badRequest().body(userRegisterResponse.userID(savedID))

        return ResponseEntity.ok(userRegisterResponse)
    }
}