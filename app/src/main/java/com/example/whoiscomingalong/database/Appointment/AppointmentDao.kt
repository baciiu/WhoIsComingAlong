package com.example.whoiscomingalong.database.Appointment

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM Appointment")
    fun getAll(): Flow<List<Appointment>>

    @Insert
    suspend fun insertAll(vararg appointments: Appointment)

    @Delete
    suspend fun delete(appointment: Appointment)
}