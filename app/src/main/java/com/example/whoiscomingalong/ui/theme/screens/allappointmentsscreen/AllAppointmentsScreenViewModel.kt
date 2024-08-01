package com.example.whoiscomingalong.ui.theme.screens.allappointmentsscreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.Network.Session.SessionManager
import com.example.whoiscomingalong.database.Appointment.AppointmentRepository
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAppointmentsScreenViewModel @Inject constructor(
    application: Application,
    private val appointmentRepository: AppointmentRepository,
    private val usersRepository: UsersRepository,
    private val sessionManager: SessionManager
) : AndroidViewModel(application) {

    private val scope = CoroutineScope(Dispatchers.IO) // Define a scope for coroutines

    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> = _appointments

    init {
        viewModelScope.launch {
            appointmentRepository.getAllAppointments().collect { appointments ->
                _appointments.value = appointments
                Log.d("AllAppScreen", "Collected appointments: $appointments")
            }
        }
    }


    fun getUserFromSession(): StateFlow<Users?> {
        val userFlow = MutableStateFlow<Users?>(null)

        val userId = sessionManager.getUserId()
        usersRepository.getUserById(userId)
            .onEach { user ->
                userFlow.value = user
            }
            .launchIn(scope) // Launch in the IO scope

        return userFlow
    }
}