package com.example.whoiscomingalong.Network.HelperData

import java.util.Date

data class SignUpRequest(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: Date,
    val company: String,
    val department: String,
    val email: String,
    val nickName: String,
    val password: String
)