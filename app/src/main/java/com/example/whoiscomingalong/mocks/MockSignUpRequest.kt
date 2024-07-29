package com.example.whoiscomingalong.mocks

data class MockSignUpRequest(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val company: String,
    val department: String,
    val email: String,
    val nickName: String,
    val password: String
)