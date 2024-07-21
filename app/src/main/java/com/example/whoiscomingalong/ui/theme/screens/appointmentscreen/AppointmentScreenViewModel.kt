package com.example.whoiscomingalong.ui.theme.screens.appointmentscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Appointment.AppointmentRepository
import com.example.whoiscomingalong.database.Group.Group
import com.example.whoiscomingalong.database.Group.GroupRepository
import com.example.whoiscomingalong.database.Restaurant.Restaurant
import com.example.whoiscomingalong.database.Restaurant.RestaurantRepository
import com.example.whoiscomingalong.database.UserToAppointment.UserToAppointment
import com.example.whoiscomingalong.database.UserToAppointment.UserToAppointmentRepository
import com.example.whoiscomingalong.database.UserToGroup.UserToGroupRepository
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AppointmentScreenViewModel @Inject constructor(
    application: Application,
    private val usersRepository: UsersRepository,
    private val groupRepository: GroupRepository,
    private val userToGroupRepository: UserToGroupRepository,
    private val restaurantRepository: RestaurantRepository,
    private val appointmentRepository: AppointmentRepository,
    private val userToAppointmentRepository: UserToAppointmentRepository
) : AndroidViewModel(application) {
    fun getAppointmentById(appointmentId: Int): Flow<Appointment> {
        return appointmentRepository.getAppointmentById(appointmentId)
    }

    fun getAllUserToAppointments(): Flow<List<UserToAppointment>> {
        return userToAppointmentRepository.getAllUserToAppointments()
    }

    fun getGroupById(groupId: Int): Flow<Group> {
        return groupRepository.getGroupById(groupId)
    }

    fun getRestaurantById(restaurantId: Int): Flow<Restaurant> {
        return restaurantRepository.getRestaurantById(restaurantId)
    }
}