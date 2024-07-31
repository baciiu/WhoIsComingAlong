package com.example.whoiscomingalong.Network.Api

import com.example.whoiscomingalong.database.UserToAppointment.UserToAppointment
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserToAppointmentApiService {

    @GET("/usersToAppointment")
    fun getAllUsersToAppointment(): Call<List<UserToAppointment>>

    @GET("/usersToAppointment/{id}")
    fun getUsersToAppointmentByAppId(@Path("id") id: Int): Call<UserToAppointment>

    @POST("/usersToAppointment")
    fun createUserToAppointment(@Body userToAppointment: UserToAppointment): Call<UserToAppointment>

    @DELETE("/usersToAppointment/{id}")
    fun deleteUserToAppointment(@Path("id") id: Int): Call<Void>
}