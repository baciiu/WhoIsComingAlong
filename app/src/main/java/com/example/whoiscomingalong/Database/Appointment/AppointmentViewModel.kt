package com.example.whoiscomingalong.Database.Appointment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.whoiscomingalong.AppDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppointmentViewModel(application: Application) : AndroidViewModel(application) {
    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).build()

    val allAppointments: StateFlow<List<Appointment>> = db.appointmentDao().getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    // todo: change name to getAllAppointments

    fun insertAppointment(appointment: Appointment) {
        viewModelScope.launch {
            db.appointmentDao().insertAll(appointment)
        }
    }
}