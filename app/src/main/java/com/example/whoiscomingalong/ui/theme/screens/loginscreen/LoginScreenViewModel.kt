package com.example.whoiscomingalong.ui.theme.screens.loginscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    application: Application,
    private val usersRepository: UsersRepository
) : AndroidViewModel(application){

    fun getAllUsers(): Flow<List<Users>> {
        return usersRepository.getAllUsers()
    }
}