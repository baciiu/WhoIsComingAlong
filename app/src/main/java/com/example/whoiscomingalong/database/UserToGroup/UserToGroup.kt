package com.example.whoiscomingalong.database.UserToGroup

import androidx.room.Entity
import androidx.room.ForeignKey
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
    ]
)
data class UserToGroup(
    val userId: Int,
    val groupId: Int
)