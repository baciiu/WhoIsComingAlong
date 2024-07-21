package com.example.whoiscomingalong.ui.theme.screens.addnewappointmentscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Appointment.AppointmentRepository
import com.example.whoiscomingalong.database.Group.Group
import com.example.whoiscomingalong.database.Group.GroupRepository
import com.example.whoiscomingalong.database.Restaurant.Restaurant
import com.example.whoiscomingalong.database.Restaurant.RestaurantRepository
import com.example.whoiscomingalong.database.UserToAppointment.UserToAppointmentRepository
import com.example.whoiscomingalong.database.UserToGroup.UserToGroup
import com.example.whoiscomingalong.database.UserToGroup.UserToGroupRepository
import com.example.whoiscomingalong.database.Users.Users
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
    private val appointmentRepository: AppointmentRepository,
    private val userToAppointmentRepository: UserToAppointmentRepository
) : AndroidViewModel(application) {
    fun addAppointment(appointment: Appointment) {
        viewModelScope.launch(Dispatchers.IO) {
            appointmentRepository.insert(appointment)
        }
    }

    fun deleteAppointment(appointment: Appointment) {
        viewModelScope.launch(Dispatchers.IO) {
            appointmentRepository.delete(appointment)
        }
    }
    fun getAllRestaurants(): Flow<List<Restaurant>> {
        return restaurantRepository.getAllRestaurants()
    }

    fun addGroup(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            groupRepository.insert(group)
        }
    }

    fun getAllGroups(): Flow<List<Group>> {
        return groupRepository.getAllGroups()
    }

    fun getUserToGroupMappings(): Flow<List<UserToGroup>> {
        return userToGroupRepository.getAllUsersToGroup()
    }

    fun getUserById(userId: Int): Flow<Users> {
        return usersRepository.getUserById(userId)
    }
}