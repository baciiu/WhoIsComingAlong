package com.example.whoiscomingalong.Network.Api

import com.example.whoiscomingalong.database.UserToGroup.UserToGroup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserToGroupApiService {
    @GET("userToGroups")
    fun getAllUserToGroups(): Call<List<UserToGroup>>

    @GET("userToGroups/{id}")
    fun getUserToGroupById(@Path("id") id: Int): Call<UserToGroup>

    @POST("userToGroups")
    fun createUserToGroup(@Body userToGroup: UserToGroup): Call<UserToGroup>

    @DELETE("userToGroups/{userId}/{groupId}")
    fun deleteUserToGroup(@Path("userId") userId: Int, @Path("groupId") groupId: Int): Call<Void>

}