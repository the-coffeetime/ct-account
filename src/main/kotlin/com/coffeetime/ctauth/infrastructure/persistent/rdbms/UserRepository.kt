package com.coffeetime.ctauth.infrastructure.persistent.rdbms

import com.coffeetime.ctauth.domain.model.UserCommentProjection
import com.coffeetime.ctauth.domain.model.UserPostProjection
import com.coffeetime.ctauth.infrastructure.entity.UserInfo
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserInfo, Long> {
    fun findByUserID(userID: Int): UserInfo?
    fun findBySocialIDGoogle(socialIDGoogle: String): UserInfo?
    fun findBySocialIDNaver(socialIDNaver: String): UserInfo?
    fun findBySocialIDKakao(socialIDKakao: String): UserInfo?
    fun findPostUserByUserID(userID: Int): UserPostProjection
    fun findCommentUserByUserID(userID: Int): UserCommentProjection
}
