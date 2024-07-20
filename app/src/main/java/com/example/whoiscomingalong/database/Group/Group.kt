package com.example.whoiscomingalong.database.Group

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Group(
    @PrimaryKey(autoGenerate = true) val groupId: Int = 0,
    val groupName: String
)