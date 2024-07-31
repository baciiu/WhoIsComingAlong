package com.example.whoiscomingalong.ui.theme.screens.appointmentscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Appointment.AppointmentRepository
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
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
    private val usersRepository: UsersRepository
) : AndroidViewModel(application) {

    private val _appointmentDetails = MutableStateFlow<Appointment?>(null)
    val appointmentDetails: StateFlow<Appointment?> = _appointmentDetails

    fun getAppointmentById(appointmentId: Int) {
        viewModelScope.launch {
            appointmentRepository.getAppointmentById(appointmentId).collect { appointment ->
                _appointmentDetails.value = appointment
            }
        }
    }

    fun getUserById(userId: Int): Flow<Users> {
        return usersRepository.getUserById(userId)
    }
}