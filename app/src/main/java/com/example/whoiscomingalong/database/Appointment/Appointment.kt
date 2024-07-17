package com.example.whoiscomingalong.database.Appointment

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Appointment(
    @PrimaryKey val appointmentId: Int,
    val groupId: Int,
    val restaurantID: Int,
    val date: Date,
    val hourMinute: Date
)