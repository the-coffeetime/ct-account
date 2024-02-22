package com.coffeetime.ctaccount.dao

import com.coffeetime.ctaccount.dto.UserCommentProjection
import com.coffeetime.ctaccount.dto.UserPostProjection
import com.coffeetime.ctaccount.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface UserRepository : CrudRepository<User, Long> {
    fun findByUserID(userID: Int): Optional<User>
    @Query("SELECT ui FROM UserInfo ui JOIN UserIDInfo uii ON ui.userID = uii.userID WHERE uii.socialID = :socialID")
    fun findBySocialID(@Param("socialID") socialID: String): Optional<User>
    fun findByUserIDOnlyPostInfo(userID: Int): UserPostProjection
    fun findByUserIDOnlyCommentInfo(userID: Int): UserCommentProjection
}
