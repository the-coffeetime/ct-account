package com.coffeetime.ctaccount.infrastructure.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "userIDInfo")
class UserIDInfo(
    @Id
    @Column(length = 255)
    var socialID: String,
    var userID: Int? = null
)
