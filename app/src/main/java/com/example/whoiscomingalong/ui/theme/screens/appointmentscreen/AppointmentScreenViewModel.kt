package com.example.whoiscomingalong.ui.theme.screens.appointmentscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.dependencyinjection.MockRepository
import com.example.whoiscomingalong.mocks.appointment.MockAppointment
import com.example.whoiscomingalong.mocks.appointment.MockAppointmentRepository
import com.example.whoiscomingalong.mocks.appointment.UserToAppointment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentScreenViewModel @Inject constructor(
    application: Application,
    @MockRepository private val mockAppointmentRepository: MockAppointmentRepository
) : AndroidViewModel(application) {

    // StateFlow to hold the list of UserToAppointment
    private val _appointments = MutableStateFlow<List<UserToAppointment>>(emptyList())
    val appointments: StateFlow<List<UserToAppointment>> = _appointments

    init {
        // Initialize by fetching all UserToAppointments
        viewModelScope.launch {
            mockAppointmentRepository.getAllUserToAppointments().collect { userToAppointments ->
                _appointments.value = userToAppointments
            }
        }
    }

    // Function to update a UserToAppointment
    fun updateUserToAppointment(userToAppointment: UserToAppointment) {
        viewModelScope.launch {
            mockAppointmentRepository.updateUserToAppointment(userToAppointment)
            // Optionally re-fetch data after update to ensure the UI is up-to-date
            mockAppointmentRepository.getAllUserToAppointments().collect { userToAppointments ->
                _appointments.value = userToAppointments
            }
        }
    }

    // Function to get all UserToAppointments
    fun getAllUserToAppointments(): Flow<List<UserToAppointment>> {
        return mockAppointmentRepository.getAllUserToAppointments()
    }

    // Function to get a specific appointment by its ID
    fun getAppointmentById(appointmentId: Int): Flow<MockAppointment> {
        return mockAppointmentRepository.getAppointmentById(appointmentId)
    }
}