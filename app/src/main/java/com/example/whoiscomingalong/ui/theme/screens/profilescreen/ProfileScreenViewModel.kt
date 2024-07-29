package com.example.whoiscomingalong.ui.theme.screens.profilescreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.mocks.MockUserRepository
import com.example.whoiscomingalong.mocks.MockUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    application: Application,
    private val userRepository: MockUserRepository
) : AndroidViewModel(application) {

    fun getUserById(userId: Int): Flow<MockUsers?> {
        return flow {
            emit(userRepository.getUserById(userId))
        }
    }

    fun updateUser(user: MockUsers) {
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }
}