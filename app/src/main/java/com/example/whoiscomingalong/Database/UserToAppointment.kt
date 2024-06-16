package com.example.whoiscomingalong

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "appointmentId"])
data class UserToAppointment(
    val userId: Int,
    val appointmentId: Int,
    val isComingAlong: Boolean
)