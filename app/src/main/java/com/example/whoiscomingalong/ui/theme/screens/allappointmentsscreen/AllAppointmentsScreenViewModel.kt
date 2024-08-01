package com.example.whoiscomingalong.ui.theme.screens.allappointmentsscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Appointment.AppointmentRepository
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAppointmentsScreenViewModel @Inject constructor(
    application: Application,
    private val appointmentRepository: AppointmentRepository,
    private val usersRepository: UsersRepository
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

    fun getUserById(userId: Int): StateFlow<Users?> {
        val userFlow = MutableStateFlow<Users?>(null)
        viewModelScope.launch {
            usersRepository.getUserById(userId).collect { user ->
                userFlow.value = user
            }
        }
        return userFlow
    }

}