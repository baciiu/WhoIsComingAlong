package com.example.whoiscomingalong.ui.theme.screens.addnewappointmentscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.dependencyinjection.MockRepository
import com.example.whoiscomingalong.mocks.MockUsers
import com.example.whoiscomingalong.mocks.appointment.MockAppointment
import com.example.whoiscomingalong.mocks.appointment.MockAppointmentRepository
import com.example.whoiscomingalong.mocks.appointment.MockGroup
import com.example.whoiscomingalong.mocks.appointment.MockRestaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddNewAppointmentScreenViewModel @Inject constructor(
    application: Application,
    @MockRepository private val appointmentRepository: MockAppointmentRepository
) : AndroidViewModel(application) {

    fun getAllGroups(): Flow<List<MockGroup>> {
        return appointmentRepository.getAllGroups()
    }

    fun getAllRestaurants(): Flow<List<MockRestaurant>> {
        return appointmentRepository.getAllRestaurants()
    }

    fun insertAppointment(
        appointmentName: String,
        groupId: Int,
        restaurantId: Int,
        date: Date,
        hourMinute: Date,
        location: String
    ) {
        val newAppointment = MockAppointment(
            appointmentId = 0, // Placeholder ID, should be set appropriately
            appointmentName = appointmentName,
            creator = MockUsers(
                userId = 1, // Assuming the creator is the first user
                firstName = "John",
                lastName = "Doe",
                nickName = "johndoe",
                dateOfBirth = "1990-01-01",
                email = "john.doe@example.com",
                password = "password123",
                company = "ExampleCorp",
                department = "Engineering"
            ),
            participants = listOf(
                MockUsers(
                    userId = 1,
                    firstName = "John",
                    lastName = "Doe",
                    nickName = "johndoe",
                    dateOfBirth = "1990-01-01",
                    email = "john.doe@example.com",
                    password = "password123",
                    company = "ExampleCorp",
                    department = "Engineering"
                )
            ),
            date = date,
            hourMinute = hourMinute,
            location = location
        )

        viewModelScope.launch(Dispatchers.IO) {
            appointmentRepository.addAppointment(newAppointment)
        }
    }
}