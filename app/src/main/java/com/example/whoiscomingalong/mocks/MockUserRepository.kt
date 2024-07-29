package com.example.whoiscomingalong.mocks


class MockUserRepository {
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
        // Mock implementation
        return null
    }
}