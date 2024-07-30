package com.example.whoiscomingalong.ui.theme.screens.appointmentscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Appointment.AppointmentRepository
import com.example.whoiscomingalong.database.UserToAppointment.UserToAppointment
import com.example.whoiscomingalong.database.UserToAppointment.UserToAppointmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentScreenViewModel @Inject constructor(
    application: Application,
    private val appointmentRepository: AppointmentRepository,
    private val userToAppointmentRepository: UserToAppointmentRepository,
) : AndroidViewModel(application) {

    // StateFlow to hold the list of UserToAppointment
    private val _appointments = MutableStateFlow<List<UserToAppointment>>(emptyList())
    val appointments: StateFlow<List<UserToAppointment>> = _appointments

    private val _appointmentDetails = MutableStateFlow<Appointment?>(null)
    val appointmentDetails: StateFlow<Appointment?> = _appointmentDetails

    init {
        // Initialize by fetching all UserToAppointments
        viewModelScope.launch {
            userToAppointmentRepository.getAllUserToAppointments().collect { userToAppointments ->
                _appointments.value = userToAppointments
            }
        }
    }

    // Function to update a UserToAppointment
    fun updateUserToAppointment(userToAppointment: UserToAppointment) {
        viewModelScope.launch {
            userToAppointmentRepository.updateUserToAppointment(userToAppointment)
            // Optionally re-fetch data after update to ensure the UI is up-to-date
            userToAppointmentRepository.getAllUserToAppointments().collect { userToAppointments ->
                _appointments.value = userToAppointments
            }
        }
    }

    // Function to get all UserToAppointments
    fun getAllUserToAppointments(): Flow<List<UserToAppointment>> {
        return userToAppointmentRepository.getAllUserToAppointments()
    }

    // Function to get a specific appointment by its ID
    fun getAppointmentById(appointmentId: Int) {
        viewModelScope.launch {
            appointmentRepository.getAppointmentById(appointmentId).collect { appointment ->
                _appointmentDetails.value = appointment
            }
        }
    }
}