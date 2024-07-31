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
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddNewAppointmentScreenViewModel @Inject constructor(
    application: Application,
    private val appointmentRepository: AppointmentRepository,
    private val groupRepository: GroupRepository,
    private val restaurantRepository: RestaurantRepository,
    private val userToAppointmentRepository: UserToAppointmentRepository,
    private val usersRepository: UsersRepository
) : AndroidViewModel(application) {

    fun getAllGroups(): Flow<List<Group>> {
        return groupRepository.getAllGroups()
    }

    fun getAllRestaurants(): Flow<List<Restaurant>> {
        return restaurantRepository.getAllRestaurants()
    }

    suspend fun insertAppointment(appointment: Appointment):Int{
        appointmentRepository.insert(appointment)
        return appointment.appointmentId
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getUsersOfAppointment(appointmentId: Int): Flow<List<Users>> {
        return userToAppointmentRepository.getAllUserToAppointments()
            .map { userToAppointments ->
                val userIds = userToAppointments.filter { it.appointmentId == appointmentId && it.isComingAlong }.map { it.userId }
                usersRepository.getAllUsers().map { users ->
                    users.filter { it.userId in userIds }
                }
            }
            .flatMapLatest { it }
    }

}