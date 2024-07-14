package com.example.whoiscomingalong

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GroupViewModel(application: Application) : AndroidViewModel(application) {
    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).build()

    val allGroups: StateFlow<List<Group>> = db.groupDao().getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertGroup(group: Group) {
        viewModelScope.launch {
            db.groupDao().insertAll(group)
        }
    }
}