package com.example.whoiscomingalong.mocks

class MockUserRepository {

    private val mockUsers = mutableListOf(
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
        ),
        MockUsers(
            userId = 2,
            firstName = "Jane",
            lastName = "Smith",
            nickName = "janesmith",
            dateOfBirth = "1992-02-02",
            email = "jane.smith@example.com",
            password = "password456",
            company = "ExampleCorp",
            department = "Marketing"
        )
    )

    fun signUp(request: MockSignUpRequest): MockSignUpResponse {
        // Mock implementation
        return MockSignUpResponse(
            userId = "mock_user_id",
            firstName = request.firstName,
            lastName = request.lastName,
            dateOfBirth = request.dateOfBirth,
            company = request.company,
            department = request.department,
            email = request.email,
            nickName = request.nickName,
            token = "mock_token"
        )
    }

    fun getUserByNickName(nickName: String): MockUsers? {
        return mockUsers.find { it.nickName == nickName }
    }

    fun getUserById(userId: Int): MockUsers? {
        return mockUsers.find { it.userId == userId }
    }


    fun authenticateUser(nickName: String, password: String): MockUsers? {
        return mockUsers.find { it.nickName == nickName && it.password == password }
    }

    fun updateUser(user: MockUsers) {
        val index = mockUsers.indexOfFirst { it.userId == user.userId }
        if (index != -1) {
            mockUsers[index] = user
        }
    }

}