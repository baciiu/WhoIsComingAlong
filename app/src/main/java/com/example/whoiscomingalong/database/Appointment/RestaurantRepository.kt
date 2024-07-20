package com.example.whoiscomingalong.database.Appointment

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppointmentRepository @Inject constructor(private val appointmentDao: AppointmentDao) {

    fun getAllAppointments(): Flow<List<Appointment>> {
        return appointmentDao.getAll()
    }

    suspend fun insert(appointment: Appointment) {
        appointmentDao.insertAll(appointment)
    }

    suspend fun insertAll(vararg appointments: Appointment) {
        appointmentDao.insertAll(*appointments)
    }

    suspend fun delete(appointment: Appointment) {
        appointmentDao.delete(appointment)
    }
}