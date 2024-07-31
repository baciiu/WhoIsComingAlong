package com.example.whoiscomingalong.Network.Api

import com.example.whoiscomingalong.database.Appointment.Appointment
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AppointmentApiService {
    @GET("/appointments")
    fun getAllAppointments(): Call<List<Appointment>>

    @GET("/appointments/{id}")
    fun getAppointmentById(@Path("id") id: Int): Call<Appointment>

    @POST("/appointments")
    fun createAppointment(@Body appointment: Appointment): Call<Appointment>

    @PUT("/appointments/{id}")
    fun updateAppointment(@Path("id") id: Int, @Body appointment: Appointment): Call<Appointment>

    @DELETE("/appointments/{id}")
    fun deleteAppointment(@Path("id") id: Int): Call<Void>
}