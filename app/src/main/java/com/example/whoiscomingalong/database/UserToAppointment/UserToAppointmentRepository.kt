package com.example.whoiscomingalong.database.UserToAppointment

import com.example.whoiscomingalong.Network.Api.UserToAppointmentApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserToAppointmentRepository @Inject constructor(
    private val userToAppointmentDao: UserToAppointmentDao,
    private val apiService: UserToAppointmentApiService) {

    fun getAllUserToAppointments(): Flow<List<UserToAppointment>> {
        fetchUserToAppointmentsFromServer()
        return userToAppointmentDao.getAll()
    }

    suspend fun insert(userToAppointment: UserToAppointment) {
        userToAppointmentDao.insertAll(userToAppointment)
        syncUserToAppointmentWithServer(userToAppointment)
    }

    suspend fun insertAll(vararg userToAppointments: UserToAppointment) {
        userToAppointmentDao.insertAll(*userToAppointments)
        syncUserToAppointmentsWithServer(userToAppointments.toList())
    }

    suspend fun delete(userToAppointment: UserToAppointment) {
        userToAppointmentDao.delete(userToAppointment)
        deleteUserToAppointmentFromServer(userToAppointment)
    }

    suspend fun updateUserToAppointment(userToAppointment: UserToAppointment) {
        userToAppointmentDao.update(userToAppointment)
        syncUserToAppointmentWithServer(userToAppointment)
    }


    private fun fetchUserToAppointmentsFromServer() {
        apiService.getAllUserToAppointments().enqueue(object : Callback<List<UserToAppointment>> {
            override fun onResponse(call: Call<List<UserToAppointment>>, response: Response<List<UserToAppointment>>) {
                if (response.isSuccessful) {
                    CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let { userToAppointments ->
                            userToAppointmentDao.insertAll(*userToAppointments.toTypedArray())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<UserToAppointment>>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    private fun fetchUserToAppointmentFromServer(userToAppointmentId: Int) {
        apiService.getUserToAppointmentById(userToAppointmentId).enqueue(object : Callback<UserToAppointment> {
            override fun onResponse(call: Call<UserToAppointment>, response: Response<UserToAppointment>) {
                if (response.isSuccessful) {
                    response.body()?.let { userToAppointment ->
                        CoroutineScope(Dispatchers.IO).launch {
                            userToAppointmentDao.insertAll(userToAppointment)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserToAppointment>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    private fun syncUserToAppointmentWithServer(userToAppointment: UserToAppointment) {
        apiService.createUserToAppointment(userToAppointment).enqueue(object : Callback<UserToAppointment> {
            override fun onResponse(call: Call<UserToAppointment>, response: Response<UserToAppointment>) {
                if (response.isSuccessful) {
                    // Handle the response
                }
            }

            override fun onFailure(call: Call<UserToAppointment>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    private fun syncUserToAppointmentsWithServer(userToAppointments: List<UserToAppointment>) {
        userToAppointments.forEach { userToAppointment ->
            syncUserToAppointmentWithServer(userToAppointment)
        }
    }

    private fun deleteUserToAppointmentFromServer(userToAppointment: UserToAppointment) {
        apiService.deleteUserToAppointment(userToAppointment.userId).enqueue(object : Callback<Void> {
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