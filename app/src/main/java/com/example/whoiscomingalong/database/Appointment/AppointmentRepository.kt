package com.example.whoiscomingalong.database.Appointment

import com.example.whoiscomingalong.Network.Api.AppointmentApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
import android.util.Log

@Singleton
class AppointmentRepository @Inject constructor(
    private val appointmentDao: AppointmentDao,
    private val apiService: AppointmentApiService) {

    fun getAllAppointments(): Flow<List<Appointment>> {
        //fetchAppointmentsFromServer()
        return appointmentDao.getAll()
    }

    fun getAppointmentById(appointmentId: Int): Flow<Appointment> {
        //fetchAppointmentFromServer(appointmentId)
        return appointmentDao.getById(appointmentId)
    }

    suspend fun insert(appointment: Appointment) {
        Log.d("APPOINTMENT",appointment.toString())
        appointmentDao.insertAll(appointment)
        //syncAppointmentWithServer(appointment)
    }

    suspend fun insertAll(vararg appointments: Appointment) {
        //syncAppointmentsWithServer(appointments.toList())
        appointmentDao.insertAll(*appointments)
    }

    suspend fun delete(appointment: Appointment) {
        appointmentDao.delete(appointment)
        //deleteAppointmentFromServer(appointment)
    }

    fun fetchAppointmentsFromServer() {
        apiService.getAllAppointments().enqueue(object : Callback<List<Appointment>> {
            override fun onResponse(call: Call<List<Appointment>>, response: Response<List<Appointment>>) {
                if (response.isSuccessful) {
                    CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let { appointments ->
                            appointmentDao.insertAll(*appointments.toTypedArray())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Appointment>>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    fun fetchAppointmentFromServer(appointmentId: Int) {
        apiService.getAppointmentById(appointmentId).enqueue(object : Callback<Appointment> {
            override fun onResponse(call: Call<Appointment>, response: Response<Appointment>) {
                if (response.isSuccessful) {
                    response.body()?.let { appointment ->
                        CoroutineScope(Dispatchers.IO).launch {
                            appointmentDao.insert(appointment)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Appointment>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    fun syncAppointmentWithServer(appointment: Appointment) {

        val newApp = Appointment(
            appointmentName = appointment.appointmentName,
            groupId = appointment.groupId,
            date = appointment.date,
            hourMinute = appointment.hourMinute,
            restaurantID = appointment.restaurantID,
            )
        apiService.createAppointment(newApp).enqueue(object : Callback<Appointment> {
            override fun onResponse(call: Call<Appointment>, response: Response<Appointment>) {
                if (response.isSuccessful) {
                    // Handle the response
                }
            }

            override fun onFailure(call: Call<Appointment>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    fun syncAppointmentsWithServer(appointments: List<Appointment>) {
        appointments.forEach { appointment ->
            syncAppointmentWithServer(appointment)
        }
    }

    fun deleteAppointmentFromServer(appointment: Appointment) {
        apiService.deleteAppointment(appointment.appointmentId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Handle the response
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle the failure
            }
        })
    }
}