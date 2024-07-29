package com.example.whoiscomingalong.Network.Api

import com.example.whoiscomingalong.database.Users.Users
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
interface UsersApiService {
    @GET("/users")
    fun getAllUsers(): Call<List<Users>>

    @GET("/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<Users>

    @POST("/users")
    fun createUser(@Body user: Users): Call<Users>

    @DELETE("/users/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Void>

}