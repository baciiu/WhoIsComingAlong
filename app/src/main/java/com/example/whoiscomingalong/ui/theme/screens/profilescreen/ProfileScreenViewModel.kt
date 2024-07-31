package com.example.whoiscomingalong.ui.theme.screens.profilescreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    application: Application,
    private val userRepository: UsersRepository
) : AndroidViewModel(application) {

    fun getUserById(userId: Int): Flow<Users?> {
        return userRepository.getUserById(userId)
    }

    fun updateUser(user: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.update(user)
        }
    }
}