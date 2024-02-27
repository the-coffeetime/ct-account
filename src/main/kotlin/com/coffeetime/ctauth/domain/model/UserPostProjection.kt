package com.coffeetime.ctauth.domain.model

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