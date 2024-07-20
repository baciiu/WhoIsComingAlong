package com.example.whoiscomingalong.database.UserToAppointment

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserToAppointmentRepository @Inject constructor(private val userToAppointmentDao: UserToAppointmentDao) {

    fun getAllUserToAppointments(): Flow<List<UserToAppointment>> {
        return userToAppointmentDao.getAll()
    }

    suspend fun insert(userToAppointment: UserToAppointment) {
        userToAppointmentDao.insertAll(userToAppointment)
    }

    suspend fun insertAll(vararg userToAppointments: UserToAppointment) {
        userToAppointmentDao.insertAll(*userToAppointments)
    }

    suspend fun delete(userToAppointment: UserToAppointment) {
        userToAppointmentDao.delete(userToAppointment)
    }
}