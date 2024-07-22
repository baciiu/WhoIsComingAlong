package com.example.whoiscomingalong.database.UserToAppointment

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Users.Users

@Entity(
    primaryKeys = ["userId", "appointmentId"],
    foreignKeys = [
        ForeignKey(
            entity = Users::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Appointment::class,
            parentColumns = ["appointmentId"],
            childColumns = ["appointmentId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserToAppointment(
    val userId: Int,
    val appointmentId: Int,
    val isComingAlong: Boolean
)