package com.example.whoiscomingalong.ui.theme.screens.addnewappointmentscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Group.GroupRepository
import com.example.whoiscomingalong.database.Restaurant.RestaurantRepository
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Appointment.AppointmentRepository
import com.example.whoiscomingalong.database.Group.Group
import com.example.whoiscomingalong.database.Restaurant.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddNewAppointmentScreenViewModel @Inject constructor(
    application: Application,
    private val groupRepository: GroupRepository,
    private val restaurantRepository: RestaurantRepository,
    private val appointmentRepository: AppointmentRepository
) : AndroidViewModel(application) {

    fun getAllGroups(): Flow<List<Group>> {
        return groupRepository.getAllGroups()
    }

    fun getAllRestaurants(): Flow<List<Restaurant>> {
        return restaurantRepository.getAllRestaurants()
    }

    fun insertAppointment(
        appointmentName: String,
        groupId: Int,
        restaurantId: Int,
        date: Date,
        hourMinute: Date
    ) {
        val newAppointment = Appointment(
            appointmentName = appointmentName,
            groupId = groupId,
            restaurantID = restaurantId,
            date = date,
            hourMinute = hourMinute
        )

        viewModelScope.launch(Dispatchers.IO) {
            appointmentRepository.insert(newAppointment)
        }
    }
}