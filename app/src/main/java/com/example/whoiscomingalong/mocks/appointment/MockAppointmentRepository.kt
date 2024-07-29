package com.example.whoiscomingalong.mocks.appointment

import com.example.whoiscomingalong.mocks.MockUsers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.Date
import javax.inject.Inject

data class MockAppointment(
    val appointmentId: Int,
    val appointmentName: String,
    val creator: MockUsers,
    val participants: List<MockUsers>,
    val date: Date,
    val hourMinute: Date
)

class MockAppointmentRepository @Inject constructor() {

    private val mockAppointments = mutableListOf(
        MockAppointment(
            appointmentId = 1,
            appointmentName = "Meeting with Team",
            creator = MockUsers(
                userId = 1,
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
            date = Date(),
            hourMinute = Date()
        ),
        MockAppointment(
            appointmentId = 2,
            appointmentName = "Lunch with Client",
            creator = MockUsers(
                userId = 2,
                firstName = "Admin",
                lastName = "Test",
                nickName = "admin",
                dateOfBirth = "1992-02-02",
                email = "admin@mail.de",
                password = "admin123",
                company = "ExampleCorp",
                department = "Marketing"
            ),
            participants = listOf(
                MockUsers(
                    userId = 2,
                    firstName = "Admin",
                    lastName = "Test",
                    nickName = "admin",
                    dateOfBirth = "1992-02-02",
                    email = "admin@mail.de",
                    password = "admin123",
                    company = "ExampleCorp",
                    department = "Marketing"
                )
            ),
            date = Date(),
            hourMinute = Date()
        )
    )

    fun getAllAppointmentsForUser(userId: Int): Flow<List<MockAppointment>> {
        return flowOf(mockAppointments.filter { it.participants.any { user -> user.userId == userId } })
    }

    fun addAppointment(appointment: MockAppointment) {
        mockAppointments.add(appointment)
    }

    fun updateAppointment(appointment: MockAppointment) {
        val index = mockAppointments.indexOfFirst { it.appointmentId == appointment.appointmentId }
        if (index != -1) {
            mockAppointments[index] = appointment
        }
    }

    fun getAllGroups(): Flow<List<MockGroup>> {
        return flowOf(listOf(MockGroup(1, "Group 1"), MockGroup(2, "Group 2")))
    }

    fun getAllRestaurants(): Flow<List<MockRestaurant>> {
        return flowOf(listOf(MockRestaurant(1, "Restaurant 1"), MockRestaurant(2, "Restaurant 2")))
    }
}

data class MockGroup(val groupId: Int, val groupName: String)
data class MockRestaurant(val restaurantId: Int, val restaurantName: String)