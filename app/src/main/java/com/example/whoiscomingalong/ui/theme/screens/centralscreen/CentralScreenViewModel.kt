package com.example.whoiscomingalong.ui.theme.screens.centralscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Appointment.AppointmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CentralScreenViewModel @Inject constructor(
    application: Application,
    private val appointmentRepository: AppointmentRepository
) : AndroidViewModel(application) {

    fun getAllAppointments(): Flow<List<Appointment>> {
        return appointmentRepository.getAllAppointments()
    }
}