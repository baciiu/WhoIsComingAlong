package com.example.whoiscomingalong.database.Users

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Users(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val firstName: String,
    val lastName: String,
    val nickName: String,
    val dateOfBirth: Date,
    val email: String,
    val password: String,
    val company: String,
    val department: String
)