package com.example.whoiscomingalong.Network.HelperData
 data class AppointmentRequest(
        val appointmentName: String,
        val groupId: Int,
        val restaurantId: Int,
        val appointmentDate: String,
        val appointmentTime: String
 )
