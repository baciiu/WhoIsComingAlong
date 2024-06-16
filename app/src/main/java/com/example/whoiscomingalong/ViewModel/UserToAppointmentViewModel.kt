package com.example.whoiscomingalong

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserToAppointmentViewModel(application: Application) : AndroidViewModel(application) {
    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).build()

    val allUserToAppointments: StateFlow<List<UserToAppointment>> = db.userToAppointmentDao().getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertUserToAppointment(userToAppointment: UserToAppointment) {
        viewModelScope.launch {
            db.userToAppointmentDao().insertAll(userToAppointment)
        }
    }
}