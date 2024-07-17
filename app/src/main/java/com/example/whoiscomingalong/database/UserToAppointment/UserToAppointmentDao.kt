package com.example.whoiscomingalong.database.UserToAppointment

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserToAppointmentDao {
    @Query("SELECT * FROM UserToAppointment")
    fun getAll(): Flow<List<UserToAppointment>>

    @Insert
    suspend fun insertAll(vararg userToAppointments: UserToAppointment)

    @Delete
    suspend fun delete(userToAppointment: UserToAppointment)
}