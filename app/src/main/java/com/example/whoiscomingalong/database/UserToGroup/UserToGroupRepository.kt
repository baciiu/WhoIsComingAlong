package com.example.whoiscomingalong.database.UserToGroup

import com.example.whoiscomingalong.Network.Api.UserToGroupApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserToGroupRepository @Inject constructor(
    private val userToGroupDao: UserToGroupDao,
    private val apiService:UserToGroupApiService) {

    fun getAllUsersToGroup(): Flow<List<UserToGroup>> {
        fetchUserToGroupsFromServer()
        return userToGroupDao.getAll()
    }

    fun getAllUsersToGroupById(groupId: Int): Flow<List<UserToGroup>> {
        fetchUserToGroupFromServer(groupId)
        return userToGroupDao.getAllUsersToGroupById(groupId)
    }

    suspend fun insert(userToGroup: UserToGroup) {
        userToGroupDao.insertAll(userToGroup)
        syncUserToGroupWithServer(userToGroup)
    }

    suspend fun insertAll(vararg userToGroups: UserToGroup) {
        userToGroupDao.insertAll(*userToGroups)
        syncUserToGroupsWithServer(userToGroups.toList())
    }

    suspend fun delete(userToGroup: UserToGroup) {
        userToGroupDao.delete(userToGroup)
        deleteUserToGroupFromServer(userToGroup)
    }

    suspend fun countUserToGroup(userId: Int, groupId: Int): Int {
        return userToGroupDao.countUserToGroup(userId, groupId)
    }

    private fun fetchUserToGroupsFromServer() {
        apiService.getAllUserToGroups().enqueue(object : Callback<List<UserToGroup>> {
            override fun onResponse(call: Call<List<UserToGroup>>, response: Response<List<UserToGroup>>) {
                if (response.isSuccessful) {
                    CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let { userToGroups ->
                            userToGroupDao.insertAll(*userToGroups.toTypedArray())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<UserToGroup>>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    private fun fetchUserToGroupFromServer(userToGroupId: Int) {
        apiService.getUserToGroupById(userToGroupId).enqueue(object : Callback<UserToGroup> {
            override fun onResponse(call: Call<UserToGroup>, response: Response<UserToGroup>) {
                if (response.isSuccessful) {
                    response.body()?.let { userToGroup ->
                        CoroutineScope(Dispatchers.IO).launch {
                            userToGroupDao.insertAll(userToGroup)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserToGroup>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    private fun syncUserToGroupWithServer(userToGroup: UserToGroup) {
        apiService.createUserToGroup(userToGroup).enqueue(object : Callback<UserToGroup> {
            override fun onResponse(call: Call<UserToGroup>, response: Response<UserToGroup>) {
                if (response.isSuccessful) {
                    // Handle the response
                }
            }

            override fun onFailure(call: Call<UserToGroup>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    private fun syncUserToGroupsWithServer(userToGroups: List<UserToGroup>) {
        userToGroups.forEach { userToGroup ->
            syncUserToGroupWithServer(userToGroup)
        }
    }

    private fun deleteUserToGroupFromServer(userToGroup: UserToGroup) {
        apiService.deleteUserToGroup(userToGroup.userId, userToGroup.groupId).enqueue(object : Callback<Void> {
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