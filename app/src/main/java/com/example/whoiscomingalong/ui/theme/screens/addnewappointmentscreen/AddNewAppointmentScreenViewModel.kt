package com.example.whoiscomingalong.ui.theme.screens.addnewappointmentscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Appointment.AppointmentRepository
import com.example.whoiscomingalong.database.Group.Group
import com.example.whoiscomingalong.database.Group.GroupRepository
import com.example.whoiscomingalong.database.Restaurant.RestaurantRepository
import com.example.whoiscomingalong.database.UserToGroup.UserToGroup
import com.example.whoiscomingalong.database.UserToGroup.UserToGroupRepository
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewAppointmentScreenViewModel @Inject constructor(
    application: Application,
    private val usersRepository: UsersRepository,
    private val groupRepository: GroupRepository,
    private val userToGroupRepository: UserToGroupRepository,
    private val restaurantRepository: RestaurantRepository,
    private val appointmentRepository: AppointmentRepository
) : AndroidViewModel(application) {

    fun getAllGroups(): Flow<List<Group>> {
        return groupRepository.getAllGroups()
    }

    fun getAllRestaurants(): Flow<List<com.example.whoiscomingalong.database.Restaurant.Restaurant>> {
        return restaurantRepository.getAllRestaurants()
    }

    fun insertAppointment(appointment: Appointment) {
        viewModelScope.launch(Dispatchers.IO) {
            appointmentRepository.insert(appointment)
        }
    }
}
