package com.example.whoiscomingalong.ui.theme.screens.centralscreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Appointment.AppointmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CentralScreenViewModel @Inject constructor(
    application: Application,
    private val appointmentRepository: AppointmentRepository
) : AndroidViewModel(application) {

    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> = _appointments

    private val TAG = "CentralScreenValidation"

    init {
        viewModelScope.launch {
            appointmentRepository.getAllAppointments().collect { mockAppointments ->
                val convertedAppointments = mockAppointments.map { mockAppointment ->
                    Appointment(
                        appointmentId = mockAppointment.appointmentId,
                        appointmentName = mockAppointment.appointmentName,
                        date = mockAppointment.date,
                        groupId = 0, // or appropriate value
                        hourMinute = mockAppointment.hourMinute,
                        restaurantID = 0, // or appropriate value,
                        location = "location",
                        creatorId = 3
                    )
                }
                _appointments.value = convertedAppointments
            }
        }
    }

    private fun validateAppointment(appointment: Appointment): Appointment {
        var updatedAppointment = appointment

        // Check if appointment name is not empty
        if (appointment.appointmentName.isBlank()) {
            Log.e(TAG, "Appointment name is blank, setting default value")
            updatedAppointment = updatedAppointment.copy(appointmentName = "Default Appointment Name")
        } else {
            Log.d(TAG, "Appointment name is valid")
        }

        // Check if groupId is a positive integer
        if (appointment.groupId <= 0) {
            Log.e(TAG, "Group ID is not valid: ${appointment.groupId}, setting default value")
            updatedAppointment = updatedAppointment.copy(groupId = 1)
        } else {
            Log.d(TAG, "Group ID is valid: ${appointment.groupId}")
        }

        // Check if restaurantID is a positive integer
        if (appointment.restaurantID <= 0) {
            Log.e(TAG, "Restaurant ID is not valid: ${appointment.restaurantID}, setting default value")
            updatedAppointment = updatedAppointment.copy(restaurantID = 1)
        } else {
            Log.d(TAG, "Restaurant ID is valid: ${appointment.restaurantID}")
        }

        // Check if date is in the future
        if (appointment.date.before(Date())) {
            Log.e(TAG, "Date is in the past: ${appointment.date}, setting default value")
            updatedAppointment = updatedAppointment.copy(date = Date())
        } else {
            Log.d(TAG, "Date is valid: ${appointment.date}")
        }

        // Check if hourMinute is a valid time
        if (appointment.hourMinute.before(Date())) {
            Log.e(TAG, "HourMinute is in the past: ${appointment.hourMinute}, setting default value")
            updatedAppointment = updatedAppointment.copy(hourMinute = Date())
        } else {
            Log.d(TAG, "HourMinute is valid: ${appointment.hourMinute}")
        }

        // Check if creatorId is a positive integer
        if (appointment.creatorId < 1) {
            Log.e(TAG, "Creator ID is not valid: ${appointment.creatorId}, setting default value")
            updatedAppointment = updatedAppointment.copy(creatorId = 1)
        } else {
            Log.d(TAG, "Creator ID is valid: ${appointment.creatorId}")
        }

        if (appointment.location.isBlank()) {
            Log.e(TAG, "Location is blank, setting default value")
            updatedAppointment = updatedAppointment.copy(location = "Default Location")
        } else {
            Log.d(TAG, "Location is valid")
        }
        return updatedAppointment
    }

    fun getAllAppointments(): Flow<List<Appointment>> {
        return _appointments
    }

    fun logout(onLogout: () -> Unit) {
        // TODO: Implement the logic to logout the user (clearing the session or token)
        onLogout()
    }
}