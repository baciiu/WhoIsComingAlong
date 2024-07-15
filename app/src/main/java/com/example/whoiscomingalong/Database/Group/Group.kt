package com.example.whoiscomingalong.Database.Group

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Group(
    @PrimaryKey val groupId: Int,
    val groupName: String
)