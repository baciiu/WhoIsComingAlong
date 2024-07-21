package com.example.whoiscomingalong.database.Appointment

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM Appointment")
    fun getAll(): Flow<List<Appointment>>

    @Query("SELECT * FROM Appointment WHERE appointmentId = :appointmentId")
    fun getById(appointmentId: Int): Flow<Appointment>

    @Insert
    suspend fun insertAll(vararg appointments: Appointment)

    @Delete
    suspend fun delete(appointment: Appointment)
}