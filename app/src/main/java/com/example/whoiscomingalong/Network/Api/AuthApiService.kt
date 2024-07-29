package com.example.whoiscomingalong.Network.Api

import com.example.whoiscomingalong.Network.HelperData.LoginDto
import com.example.whoiscomingalong.database.Users.Users
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("/auth/register")
    fun registerUser(@Body user: Users): Call<Users>

    @POST("/auth/login")
    fun loginUser(@Body login: LoginDto): Call<String>
}