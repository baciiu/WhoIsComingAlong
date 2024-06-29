package com.example.whoiscomingalong

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime
import java.util.*

@Entity
data class Appointment(
    @PrimaryKey val appointmentId: Int,
    val groupId: Int,
    val restaurantID: Int,
    val date: Date,
    val hourMinute: Date
)