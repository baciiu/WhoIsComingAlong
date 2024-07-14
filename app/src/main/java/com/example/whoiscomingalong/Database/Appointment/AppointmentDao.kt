package com.example.whoiscomingalong.Database.Appointment

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM Appointment")
    fun getAll(): Flow<List<Appointment>>

    @Insert
    fun insertAll(vararg appointments: Appointment)

    @Delete
    fun delete(appointment: Appointment)
}