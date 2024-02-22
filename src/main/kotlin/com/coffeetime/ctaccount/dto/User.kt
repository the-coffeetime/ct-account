package com.coffeetime.ctaccount.dto

import java.time.Instant

data class UserRegisterReq(
    val nickName: String,
    val profilePictureURL: String? = null,
    val jobs: Iterable<String>,
)

data class UserRegisterRes(
    val userID: Int,
    
)

data class UserRes(
    val userID: Int,
    val createdAt: Instant,
    val lastLogin: Instant,
    val nickName: String,
    val email: String? = null,
    val profilePictureURL: String? = null,
    val verified: Boolean,
    val reports: Int,
    val jobs: List<String>,
    val company: String? = null,
    val introduction: String? = null,
    val status: String,
)

interface UserPostProjection {
    /*
    게시글 표현에 필요한 유저 정보만 조회
     */
    fun getNickName(): String
    fun getProfilePictureURL(): String?
    fun getVerified(): Boolean
    fun getJob1(): String?
    fun getCompany(): String?
    fun getStatus(): String?
}

interface UserCommentProjection {
    /*
    댓글 표현에 필요한 유저 정보만 조회
     */
    fun getNickName(): String
    fun getProfilePictureURL(): String?
    fun getVerified(): Boolean
    fun getJob1(): String?
    fun getCompany(): String?
    fun getStatus(): String?
}