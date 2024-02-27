package com.coffeetime.ctauth.infrastructure.persistent.rdbms

import com.coffeetime.ctauth.domain.model.UserCommentProjection
import com.coffeetime.ctauth.domain.model.UserPostProjection
import com.coffeetime.ctauth.infrastructure.entity.UserInfo
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository : CrudRepository<UserInfo, Long> {
    fun findByUserID(userID: Int): UserInfo?
    @Query("SELECT ui FROM UserInfo ui JOIN UserIDInfo uii ON ui.userID = uii.userID WHERE uii.socialID = :socialID")
    fun findBySocialID(@Param("socialID") socialID: String): UserInfo?
    fun findPostUserByUserID(userID: Int): UserPostProjection

    fun findCommentUserByUserID(userID: Int): UserCommentProjection
}
