package com.example.whoiscomingalong.database.Group

import com.example.whoiscomingalong.Network.Api.GroupApiService
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
class GroupRepository @Inject constructor(
    private val groupDao: GroupDao,
    private val apiService: GroupApiService
) {

    fun getAllGroups(): Flow<List<Group>> {
        fetchGroupsFromServer()
        return groupDao.getAll()
    }

    fun getGroupById(groupId: Int): Flow<Group> {
        fetchGroupFromServer(groupId)
        return groupDao.getById(groupId)
    }

    suspend fun insert(group: Group) {
        groupDao.insert(group)
        syncGroupWithServer(group)
    }

    suspend fun insertAll(vararg groups: Group) {
        groupDao.insertAll(*groups)
        syncGroupsWithServer(groups.toList())
    }

    suspend fun update(group: Group) {
        groupDao.update(group)
        syncGroupWithServer(group)
    }

    suspend fun delete(group: Group) {
        groupDao.delete(group)
        deleteGroupFromServer(group)
    }

    private fun fetchGroupsFromServer() {
        apiService.getAllGroups().enqueue(object : Callback<List<Group>> {
            override fun onResponse(call: Call<List<Group>>, response: Response<List<Group>>) {
                if (response.isSuccessful) {
                    CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let { groups ->
                            groupDao.insertAll(*groups.toTypedArray())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Group>>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    private fun fetchGroupFromServer(groupId: Int) {
        apiService.getGroupById(groupId).enqueue(object : Callback<Group> {
            override fun onResponse(call: Call<Group>, response: Response<Group>) {
                if (response.isSuccessful) {
                    response.body()?.let { group ->
                        CoroutineScope(Dispatchers.IO).launch {
                            groupDao.insert(group)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Group>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    private fun syncGroupWithServer(group: Group) {
        apiService.createGroup(group).enqueue(object : Callback<Group> {
            override fun onResponse(call: Call<Group>, response: Response<Group>) {
                if (response.isSuccessful) {
                    // Handle the response
                }
            }

            override fun onFailure(call: Call<Group>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    private fun syncGroupsWithServer(groups: List<Group>) {
        groups.forEach { group ->
            syncGroupWithServer(group)
        }
    }

    private fun deleteGroupFromServer(group: Group) {
        apiService.deleteGroup(group.groupId).enqueue(object : Callback<Void> {
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