package com.example.whoiscomingalong

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserToAppointmentDao {
    @Query("SELECT * FROM UserToAppointment")
    fun getAll(): Flow<List<UserToAppointment>>

    @Insert
    fun insertAll(vararg userToAppointments: UserToAppointment)

    @Delete
    fun delete(userToAppointment: UserToAppointment)
}