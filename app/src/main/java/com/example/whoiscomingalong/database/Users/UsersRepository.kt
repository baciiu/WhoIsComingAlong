package com.example.whoiscomingalong.database.Users

import com.example.whoiscomingalong.Network.Api.UsersApiService
import com.example.whoiscomingalong.Network.HelperData.SignUpRequest
import com.example.whoiscomingalong.Network.HelperData.SignUpResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

// repositories are classes used by Hilt to create functions that
// can be used by the view models
// they are created only once during the application, the DAOs of the
// corresponding DAO classes are injected during initialization

@Singleton
class UsersRepository @Inject constructor(
    private val usersDao: UsersDao,
    private val apiService: UsersApiService) {

    fun getUserById(userId: Int): Flow<Users> {
        fetchUserFromServer(userId)
        return usersDao.getUserById(userId)
    }

    fun getAllUsers(): Flow<List<Users>> {
        fetchUsersFromServer()
        return usersDao.getAllUsers()
    }

    suspend fun insert(user: Users) {
        usersDao.insert(user)
        syncUserWithServer(user)
    }

    suspend fun insertAll(vararg users: Users) {
        usersDao.insertAll(*users)
        syncUsersWithServer(users.toList())
    }

    suspend fun delete(user: Users) {
        usersDao.delete(user)
        deleteUserFromServer(user)
    }

    suspend fun update(user: Users) {
        fetchUserFromServer(userId = user.userId)
        usersDao.update(user)
    }
    suspend fun signUp(request: SignUpRequest): SignUpResponse {
        val newUser = Users(
            userId = 0,
            firstName = request.firstName,
            lastName = request.lastName,
            nickName = request.nickName,
            dateOfBirth = request.dateOfBirth,
            email = request.email,
            password = request.password,
            company = request.company,
            department = request.department
        )
        usersDao.insert(newUser)
        syncUserWithServer(newUser)

        return SignUpResponse(
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

    suspend fun authenticateUser(nickName: String, password: String): Users? {
        fetchUsersFromServer()
        return usersDao.authenticateUser(nickName,password)
    }

    suspend fun getUserByNickName(nickName: String): Users? {
        fetchUsersFromServer()
        return usersDao.getUserByNickName(nickName)
    }


    fun fetchUsersFromServer() {
        apiService.getAllUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let { users ->
                            usersDao.insertAll(*users.toTypedArray())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    fun fetchUserFromServer(userId: Int) {
        apiService.getUserById(userId).enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful) {
                    response.body()?.let { user ->
                        CoroutineScope(Dispatchers.IO).launch {
                            usersDao.insert(user)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    fun syncUserWithServer(user: Users) {
        apiService.createUser(user).enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful) {
                    // Handle the response
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    fun syncUsersWithServer(users: List<Users>) {
        users.forEach { user ->
            syncUserWithServer(user)
        }
    }

    fun deleteUserFromServer(user: Users) {
        apiService.deleteUser(user.userId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Handle the response
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle the failure
            }
        })
    }
}