package com.example.whoiscomingalong.ViewModelsUnused

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// @HiltViewModel
class UserViewModel //@Inject
 constructor(
    application: Application,
    private val usersRepository: UsersRepository
) : AndroidViewModel(application) {

    /* todo: delete later
    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).build() */

    // val allUsers: StateFlow<List<Users>> = db.usersDao().getAllUsers()
    //    .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // remarks: .stateIn transforms the Flow created in the dao (UsersDao) into a StateFlow
    // thereby allowing the ViewModel to use the data from the dao,
    // default is an empty list

    /*
    val allUsers: StateFlow<List<Users>> = usersRepository.allUsers
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertUser(user: Users) {
        viewModelScope.launch (Dispatchers.IO) {
            usersRepository.insert(user)
            // db.usersDao().insertAll(user)

        }
    } */
}