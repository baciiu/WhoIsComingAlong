package com.example.whoiscomingalong.ui.theme.screens.allappointmentsscreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Appointment.AppointmentRepository
import com.example.whoiscomingalong.database.UserToAppointment.UserToAppointmentRepository
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AllAppointmentsScreenViewModel @Inject constructor(
    application: Application,
    private val appointmentRepository: AppointmentRepository,
    private val userToAppointmentRepository: UserToAppointmentRepository,
    private val usersRepository: UsersRepository,
) : AndroidViewModel(application) {

    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> = _appointments

    private val TAG = "AppointmentsFetching"

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getUsersOfAppointment(appointmentId: Int): Flow<List<Users>> {
        return userToAppointmentRepository.getAllUserToAppointments()
            .map { userToAppointments ->
                val userIds = userToAppointments.filter { it.appointmentId == appointmentId && it.isComingAlong }.map { it.userId }
                usersRepository.getAllUsers().map { users ->
                    users.filter { it.userId in userIds }
                }
            }
            .flatMapLatest { it }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    fun getAllAppointmentsForUser(userId: Int): Flow<List<Appointment>> {
        return userToAppointmentRepository.getAllUserToAppointments()
            .map { userToAppointments ->
                val appointmentIds = userToAppointments.filter { it.userId == userId && it.isComingAlong }.map { it.appointmentId }
                appointmentRepository.getAllAppointments().map { appointments ->
                    appointments.filter { it.appointmentId in appointmentIds }
                }
            }
            .flatMapLatest { it }
    }

    init {
        viewModelScope.launch {
            appointmentRepository.getAllAppointments()
            Log.d(TAG, "All appointments fetched successfully")


            getAllAppointmentsForUser(1).collect { appointments ->
                _appointments.value = appointments
                Log.d(TAG, "Appointments for user 1 fetched successfully")

            }
        }
    }

    fun validateAppointmentData(appointments: List<Appointment>): List<Appointment> {
        val validAppointments = appointments.filter { appointment ->
            val isValid = appointment.appointmentName.isNotBlank() &&
                    appointment.groupId > 0 &&
                    appointment.restaurantID > 0 &&
                    appointment.date.after(Date()) &&
                    appointment.hourMinute.after(Date()) &&
                    appointment.creatorId > 0 &&
                    appointment.location.isNotBlank()
            if (!isValid) {
                Log.e(TAG, "Invalid appointment found: $appointment")
            }
            isValid
        }
        return validAppointments
    }
}