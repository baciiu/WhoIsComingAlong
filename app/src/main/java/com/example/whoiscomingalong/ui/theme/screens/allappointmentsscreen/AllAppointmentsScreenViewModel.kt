package com.example.whoiscomingalong.ui.theme.screens.allappointmentsscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.mocks.appointment.MockAppointment
import com.example.whoiscomingalong.mocks.appointment.MockAppointmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAppointmentsScreenViewModel @Inject constructor(
    application: Application,
    private val appointmentRepository: MockAppointmentRepository
) : AndroidViewModel(application) {

    private val _appointments = MutableStateFlow<List<MockAppointment>>(emptyList())
    val appointments: StateFlow<List<MockAppointment>> = _appointments

    init {
        viewModelScope.launch {
            appointmentRepository.getAllAppointmentsForUser(1).collect { appointments ->
                _appointments.value = appointments
            }
        }
    }
}