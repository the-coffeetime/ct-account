package com.coffeetime.ctauth.domain.service

import com.coffeetime.ctauth.common.property.google
import com.coffeetime.ctauth.common.property.kakao
import com.coffeetime.ctauth.common.property.naver
import com.coffeetime.ctauth.domain.model.UserRegisterRequest
import com.coffeetime.ctauth.domain.model.UserRegisterResponse
import com.coffeetime.ctauth.domain.model.UserResponse
import com.coffeetime.ctauth.infrastructure.entity.UserInfo
import com.coffeetime.ctauth.infrastructure.persistent.rdbms.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    @Autowired
    private lateinit var repository: UserRepository

    override fun saveOrUpdateUser(userReq: UserRegisterRequest): UserRegisterResponse {
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
        when (userReq.socialService) {
            google -> user.socialIDGoogle = userReq.socialID
            naver -> user.socialIDNaver = userReq.socialID
            kakao -> user.socialIDKakao = userReq.socialID
        }
        try {
            repository.save(user)
        } catch (e: Exception) {
            return UserRegisterResponse(-4)
        }

        return UserRegisterResponse(user.userID?:-5)
    }
    override fun findByUserID(userID: Long): UserResponse? {
        val response = repository.findById(userID)
        return if (response.isPresent) {
            response.get().toUserResponse()
        } else null
    }

    override fun findBySocialID(socialID: String, socialService: String): UserResponse? {
        val response = when (socialService) {
            google -> repository.findBySocialIDGoogle(socialID)
            naver -> repository.findBySocialIDNaver(socialID)
            kakao -> repository.findBySocialIDKakao(socialID)
            else -> null
        }
        return if (response != null) {
            response.toUserResponse()
        } else null
    }

    override fun findAll(): Iterable<UserResponse> {
        val users = repository.findAll()
        val userResponses = mutableListOf<UserResponse>()
        for (user in users) {
            userResponses.add(user.toUserResponse())
        }
        return userResponses
    }
}

fun UserInfo.toUserResponse(): UserResponse {
    return UserResponse(
        userID = this.userID ?: throw IllegalArgumentException("UserID cannot be null"),
        createdAt = this.createdAt,
        lastLogin = this.lastLogin,
        nickName = this.nickName,
        socialIDGoogle = this.socialIDGoogle,
        socialIDNaver = this.socialIDNaver,
        socialIDKakao = this.socialIDKakao,
        email = this.email,
        profilePictureURL = this.profilePictureURL,
        verified = this.verified,
        jobs = listOfNotNull(this.job1, this.job2, this.job3, this.job4),
        company = this.company,
        introduction = this.introduction,
        status = this.status
    )
}
