package com.example.whoiscomingalong.ui.theme.screens.centralscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.dependencyinjection.MockRepository
import com.example.whoiscomingalong.mocks.appointment.MockAppointmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CentralScreenViewModel @Inject constructor(
    application: Application,
    @MockRepository private val mockAppointmentRepository: MockAppointmentRepository
) : AndroidViewModel(application) {

    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> = _appointments

    init {
        viewModelScope.launch {
            mockAppointmentRepository.getAllAppointmentsForUser(1).collect { mockAppointments ->
                val convertedAppointments = mockAppointments.map { mockAppointment ->
                    Appointment(
                        appointmentId = mockAppointment.appointmentId,
                        appointmentName = mockAppointment.appointmentName,
                        date = mockAppointment.date,
                        groupId = 0, // or appropriate value
                        hourMinute = mockAppointment.hourMinute,
                        restaurantID = 0 // or appropriate value
                    )
                }
                _appointments.value = convertedAppointments
            }
        }
    }

    fun getAllAppointments(): Flow<List<Appointment>> {
        return _appointments
    }

    fun logout(onLogout: () -> Unit) {
        // TODO: Implement the logic to logout the user (clearing the session or token)
        onLogout()
    }
}