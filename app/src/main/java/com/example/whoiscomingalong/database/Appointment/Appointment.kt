package com.example.whoiscomingalong.database.Appointment

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Appointment(
    @PrimaryKey(autoGenerate = true) val appointmentId: Int = 0,
    val appointmentName: String,
    val groupId: Int,
    val restaurantID: Int,
    val date: Date,
    val hourMinute: Date
)
