package com.example.whoiscomingalong.Network.Api

import com.example.whoiscomingalong.database.Group.Group
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.GET

interface GroupApiService {
    @GET("groups")
    fun getAllGroups(): Call<List<Group>>

    @GET("groups/{id}")
    fun getGroupById(@Path("id") groupId: Int): Call<Group>

    @POST("groups")
    fun createGroup(@Body group: Group): Call<Group>

    @PUT("groups/{id}")
    fun updateGroup(@Path("id") groupId: Int, @Body group: Group): Call<Group>

    @DELETE("groups/{id}")
    fun deleteGroup(@Path("id") groupId: Int): Call<Void>
}


