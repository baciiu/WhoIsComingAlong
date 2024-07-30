package com.example.whoiscomingalong.database.Appointment

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.whoiscomingalong.database.Group.Group
import com.example.whoiscomingalong.database.Restaurant.Restaurant
import java.util.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Group::class,
            parentColumns = ["groupId"],
            childColumns = ["groupId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Restaurant::class,
            parentColumns = ["restaurantId"],
            childColumns = ["restaurantID"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Appointment(
    @PrimaryKey(autoGenerate = true) val appointmentId: Int = 0,
    val appointmentName: String,
    val groupId: Int,
    val restaurantID: Int,
    val date: Date,
    val hourMinute: Date,
    val creatorId: Int,
    val location: String
)