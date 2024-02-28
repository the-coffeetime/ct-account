package com.coffeetime.ctauth.domain.service

import com.coffeetime.ctauth.common.property.google
import com.coffeetime.ctauth.common.property.kakao
import com.coffeetime.ctauth.common.property.naver
import com.coffeetime.ctauth.infrastructure.entity.UserInfo
import com.coffeetime.ctauth.infrastructure.persistent.rdbms.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    @Autowired
    private lateinit var repository: UserRepository

    override fun saveOrUpdateUser(user: UserInfo): UserInfo {
        return repository.save(user)
    }
    override fun findByUserID(userID: Int): UserInfo? =
        repository.findByUserID(userID)
    override fun findBySocialID(socialID: String, socialService: String): UserInfo? {
        return when (socialService) {
            google -> repository.findBySocialIDGoogle(socialID)
            naver -> repository.findBySocialIDNaver(socialID)
            kakao -> repository.findBySocialIDKakao(socialID)
            else -> null
        }
    }

    override fun findAll(): Iterable<UserInfo> {
        return repository.findAll()
    }
}