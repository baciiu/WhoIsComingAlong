package com.example.whoiscomingalong.ui.theme.screens.signupscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// each of the view models has to be declared a HiltViewModel for Hilt to
// know which view models to use
// additionally, Hilt works with constructor injection, where all the later used
// repositories are injected into the view model

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    application: Application,
    private val usersRepository: UsersRepository
) : AndroidViewModel(application) {

    fun insertUser(user: Users) {
        viewModelScope.launch (Dispatchers.IO){
            usersRepository.insert(user)
        }
    }
}