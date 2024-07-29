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
            firstName = "Admin",
            lastName = "Test",
            nickName = "admin",
            dateOfBirth = "1992-02-02",
            email = "admin@mail.de",
            password = "admin123",
            company = "ExampleCorp",
            department = "Marketing"
        )
    )

    fun signUp(request: MockSignUpRequest): MockSignUpResponse {
        val newUser = MockUsers(
            userId = mockUsers.size + 1,
            firstName = request.firstName,
            lastName = request.lastName,
            nickName = request.nickName,
            dateOfBirth = request.dateOfBirth,
            email = request.email,
            password = request.password,
            company = request.company,
            department = request.department
        )
        mockUsers.add(newUser)

        return MockSignUpResponse(
            userId = newUser.userId.toString(),
            firstName = newUser.firstName,
            lastName = newUser.lastName,
            dateOfBirth = newUser.dateOfBirth,
            company = newUser.company,
            department = newUser.department,
            email = newUser.email,
            nickName = newUser.nickName,
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