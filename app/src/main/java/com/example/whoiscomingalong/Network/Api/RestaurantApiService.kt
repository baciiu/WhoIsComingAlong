package com.example.whoiscomingalong.Network.Api

import com.example.whoiscomingalong.database.Group.Group
import com.example.whoiscomingalong.database.Restaurant.Restaurant
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestaurantApiService {

    @GET("restaurants")
    fun getAllRestaurants(): Call<List<Restaurant>>

    @GET("restaurants/{id}")
    fun getRestaurantById(@Path("id") restaurantId: Int): Call<Restaurant>

    @POST("restaurants")
    fun createRestaurant(@Body restaurant: Restaurant): Call<Restaurant>

    @PUT("restaurants/{id}")
    fun updateRestaurant(@Path("id") restaurantId: Int, @Body restaurant: Restaurant): Call<Restaurant>

    @DELETE("restaurants/{id}")
    fun deleteRestaurant(@Path("id") restaurantId: Int): Call<Void>
}