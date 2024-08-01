package com.example.whoiscomingalong.ui.theme.screens.profilescreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.Network.Session.SessionManager
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    application: Application,
    private val usersRepository: UsersRepository,
    private val sessionManager: SessionManager
) : AndroidViewModel(application) {

    fun getUserFromSession(): Flow<Users?> {
        return flow {
            val userId = sessionManager.getUserId()
            val user = usersRepository.getUserById(userId).firstOrNull()
            emit(user)
        }
    }

    fun updateUser(user: Users) {
        viewModelScope.launch {
            usersRepository.update(user)
        }
    }
}