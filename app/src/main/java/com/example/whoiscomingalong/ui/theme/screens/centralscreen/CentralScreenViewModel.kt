package com.example.whoiscomingalong.ui.theme.screens.centralscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.Network.Session.SessionManager
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Appointment.AppointmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CentralScreenViewModel @Inject constructor(
    application: Application,
    private val appointmentRepository: AppointmentRepository,
    private val sessionManager: SessionManager
) : AndroidViewModel(application) {

    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> = _appointments

    init {
        viewModelScope.launch {
            appointmentRepository.getAllAppointments().collect { appointments ->
                _appointments.value = appointments
            }
        }
    }

    fun getAllAppointments(): Flow<List<Appointment>> {
        return appointmentRepository.getAllAppointments()
    }

    fun logout(onLogout: () -> Unit) {
        sessionManager.clearSession()
        onLogout()
    }
}