package com.example.whoiscomingalong.ui.theme.screens.addnewappointmentscreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
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

    private val TAG = "AppointmentValidation"

    fun getAllGroups(): Flow<List<Group>> {
        return groupRepository.getAllGroups()
    }

    fun getAllRestaurants(): Flow<List<Restaurant>> {
        return restaurantRepository.getAllRestaurants()
    }

    suspend fun insertAppointment(appointment: Appointment): Int {
        val validAppointment = checkIfAppointmentValid(appointment)
        appointmentRepository.insert(validAppointment)
        Log.d(TAG, "Appointment inserted successfully with ID: ${validAppointment.appointmentId}")
        return validAppointment.appointmentId
    }

    fun checkIfAppointmentValid(appointment: Appointment): Appointment {
        var updatedAppointment = appointment

        // Check if appointment name is not empty
        if (appointment.appointmentName.isBlank()) {
            Log.e(TAG, "Appointment name is blank, setting default value")
            updatedAppointment = updatedAppointment.copy(appointmentName = "Default Appointment Name")
        } else {
            Log.d(TAG, "Appointment name is valid")
        }

        // Check if groupId is a positive integer
        if (appointment.groupId <= 0) {
            Log.e(TAG, "Group ID is not valid: ${appointment.groupId}, setting default value")
            updatedAppointment = updatedAppointment.copy(groupId = 15)
        } else {
            Log.d(TAG, "Group ID is valid: ${appointment.groupId}")
        }

        // Check if restaurantID is a positive integer
        if (appointment.restaurantID <= 0) {
            Log.e(TAG, "Restaurant ID is not valid: ${appointment.restaurantID}, setting default value")
            updatedAppointment = updatedAppointment.copy(restaurantID = 1)
        } else {
            Log.d(TAG, "Restaurant ID is valid: ${appointment.restaurantID}")
        }

        // Check if date is in the future
        if (appointment.date.before(Date())) {
            Log.e(TAG, "Date is in the past: ${appointment.date}, setting default value")
            updatedAppointment = updatedAppointment.copy(date = Date())
        } else {
            Log.d(TAG, "Date is valid: ${appointment.date}")
        }

        // Check if hourMinute is a valid time
        if (appointment.hourMinute.before(Date())) {
            Log.e(TAG, "HourMinute is in the past: ${appointment.hourMinute}, setting default value")
            updatedAppointment = updatedAppointment.copy(hourMinute = Date())
        } else {
            Log.d(TAG, "HourMinute is valid: ${appointment.hourMinute}")
        }

        // Check if creatorId is a positive integer
        if (appointment.creatorId < 1) {
            Log.e(TAG, "Creator ID is not valid: ${appointment.creatorId}, setting default value")
            updatedAppointment = updatedAppointment.copy(creatorId = 1)
        } else {
            Log.d(TAG, "Creator ID is valid: ${appointment.creatorId}")
        }

        if (appointment.location.isBlank()) {
            Log.e(TAG, "Location is blank, setting default value")
            updatedAppointment = updatedAppointment.copy(location = "Default Location")
        } else {
            Log.d(TAG, "Location is valid")
        }
        return updatedAppointment
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