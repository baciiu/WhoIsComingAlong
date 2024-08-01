package com.example.whoiscomingalong.Network.Api

import com.example.whoiscomingalong.database.UserToAppointment.UserToAppointment
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
interface UserToAppointmentApiService {

    @GET("userToAppointments")
    fun getAllUserToAppointments(): Call<List<UserToAppointment>>

    @GET("userToAppointments/{id}")
    fun getUserToAppointmentById(@Path("id") id: Int): Call<UserToAppointment>

    @POST("userToAppointments")
    fun createUserToAppointment(@Body userToAppointment: UserToAppointment): Call<UserToAppointment>

    @DELETE("userToAppointments/{id}")
    fun deleteUserToAppointment(@Path("id") id: Int): Call<Void>
}