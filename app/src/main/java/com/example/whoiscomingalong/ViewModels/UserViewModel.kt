package com.example.whoiscomingalong

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).build()

    val allUsers: StateFlow<List<Users>> = db.usersDao().getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    // remarks: .stateIn transforms the Flow created in the dao (UsersDao) into a StateFlow
    // thereby allowing the ViewModel to use the data from the dao,
    // default is an empty list

    fun insertUser(user: Users) {
        viewModelScope.launch {
            db.usersDao().insertAll(user)
        }
    }
}