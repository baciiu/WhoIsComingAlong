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
    val hourMinute: Date,
    val location : String
)

data class UserToAppointment(
    val userId: Int,
    val appointmentId: Int,
    val isComingAlong: Boolean
)

data class MockGroup(val groupId: Int, val groupName: String)
data class MockRestaurant(val restaurantId: Int, val restaurantName: String)

class MockAppointmentRepository @Inject constructor() {

    private val mockAppointments = mutableListOf(
        MockAppointment(
            appointmentId = 1,
            appointmentName = "Dinner with Team",
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
            hourMinute = Date(),
            location = "Steakhouse Giuseppe"
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
            hourMinute = Date(),
            location = "Vegan Cuisine Club"
        )
    )

    private val userToAppointments = mutableListOf(
        UserToAppointment(
            userId = 1,
            appointmentId = 1,
            isComingAlong = true
        ),
        UserToAppointment(
            userId = 2,
            appointmentId = 2,
            isComingAlong = false
        )
    )

    fun getAllAppointmentsForUser(userId: Int): Flow<List<MockAppointment>> {
        return flowOf(mockAppointments.filter { it.participants.any { user -> user.userId == userId } })
    }

    fun getAppointmentById(appointmentId: Int): Flow<MockAppointment> {
        return flowOf(mockAppointments.first { it.appointmentId == appointmentId })
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

    fun getAllUserToAppointments(): Flow<List<UserToAppointment>> {
        return flowOf(userToAppointments)
    }

    fun updateUserToAppointment(userToAppointment: UserToAppointment) {
        val index = userToAppointments.indexOfFirst { it.userId == userToAppointment.userId && it.appointmentId == userToAppointment.appointmentId }
        if (index != -1) {
            userToAppointments[index] = userToAppointment
        }
    }

    fun getGroupById(groupId: Int): Flow<MockGroup> {
        return flowOf(MockGroup(groupId, "Group $groupId"))
    }

    fun getRestaurantById(restaurantId: Int): Flow<MockRestaurant> {
        return flowOf(MockRestaurant(restaurantId, "Restaurant $restaurantId"))
    }
}