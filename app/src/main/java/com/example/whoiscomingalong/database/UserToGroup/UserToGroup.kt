package com.example.whoiscomingalong.database.UserToGroup

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.whoiscomingalong.database.Group.Group
import com.example.whoiscomingalong.database.Users.Users

@Entity(
    primaryKeys = ["userId", "groupId"],
    foreignKeys = [
        ForeignKey(
            entity = Users::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Group::class,
            parentColumns = ["groupId"],
            childColumns = ["groupId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["groupId"])]
)
data class UserToGroup(
    val userId: Int,
    val groupId: Int
)