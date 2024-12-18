package com.example.whoiscomingalong.database.Users

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// entities are the structure of the tables, in our application
// six tables are defined, all have a primary key or a
// composite primary key (consisting of 2 foreign key, whose
// combination is unique

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