package com.example.whoiscomingalong.Network.HelperData

import java.util.Date

data class AppointmentRequest(
        val appointmentName: String,
        val groupId: Int,
        val restaurantId: Int,
        val appointmentDate: Date,
        val appointmentTime: Date,
        val creatorId: Int = 0,
        val location: String
 )
