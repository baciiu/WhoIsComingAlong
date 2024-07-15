package com.example.whoiscomingalong.Database.UserToGroup

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "groupId"])
data class UserToGroup(
    val userId: Int,
    val groupId: Int
)