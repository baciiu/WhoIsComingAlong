package com.example.whoiscomingalong.mocks

class MockUserRepository {

    private val mockUsers = listOf(
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
    )

    suspend fun signUp(request: MockSignUpRequest): MockSignUpResponse {
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

    suspend fun getUserByNickName(nickName: String): MockUsers? {
        return mockUsers.find { it.nickName == nickName }
    }


    suspend fun authenticateUser(nickName: String, password: String): MockUsers? {
        return mockUsers.find { it.nickName == nickName && it.password == password }
    }


}