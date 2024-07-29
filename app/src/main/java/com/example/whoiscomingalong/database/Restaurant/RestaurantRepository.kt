package com.example.whoiscomingalong.database.Restaurant

import android.util.Log
import com.example.whoiscomingalong.Network.Api.RestaurantApiService
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
class RestaurantRepository @Inject constructor(
    private val restaurantDao: RestaurantDao,
    private val apiService: RestaurantApiService
    ) {

    var TAG = "RESTAURANT REPO"

    fun getAllRestaurants(): Flow<List<Restaurant>> {
        fetchRestaurantsFromServer()
        return restaurantDao.getAll()
    }

    fun getRestaurantById(restaurantId: Int): Flow<Restaurant> {
        fetchRestaurantFromServer(restaurantId)
        return restaurantDao.getById(restaurantId)
    }

    suspend fun insert(restaurant: Restaurant) {
        restaurantDao.insertAll(restaurant)
        syncRestaurantWithServer(restaurant)
    }

    suspend fun insertAll(vararg restaurants: Restaurant) {
        restaurantDao.insertAll(*restaurants)
        syncRestaurantsWithServer(restaurants.toList())
    }

    suspend fun update(restaurant: Restaurant){
        restaurantDao.update(restaurant)
        syncRestaurantWithServer(restaurant)
    }

    suspend fun delete(restaurant: Restaurant) {
        restaurantDao.delete(restaurant)
        deleteRestaurantFromServer(restaurant)
    }

    private fun fetchRestaurantsFromServer() {
        apiService.getAllRestaurants().enqueue(object : Callback<List<Restaurant>> {
            override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
                if (response.isSuccessful) {
                    CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let { restaurants ->
                            restaurantDao.insertAll(*restaurants.toTypedArray())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    private fun fetchRestaurantFromServer(restaurantId: Int) {
        apiService.getRestaurantById(restaurantId = restaurantId).enqueue(object : Callback<Restaurant> {
            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                if (response.isSuccessful) {
                    response.body()?.let { restaurant ->
                        CoroutineScope(Dispatchers.IO).launch {
                            restaurantDao.insert(restaurant)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    private fun syncRestaurantWithServer(restaurant: Restaurant) {
        apiService.createRestaurant(restaurant).enqueue(object : Callback<Restaurant> {
            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                if (response.isSuccessful) {
                    // Handle the response
                    Log.d(TAG,"add restaurant ${restaurant.restaurantName}")
                }
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                // Handle the failure
            }
        })
    }

    private fun syncRestaurantsWithServer(restaurants: List<Restaurant>) {
        restaurants.forEach { restaurant ->
            syncRestaurantWithServer(restaurant)
        }
    }

    private fun deleteRestaurantFromServer(restaurant: Restaurant) {
        apiService.deleteRestaurant(restaurant.restaurantId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Handle the response
                    Log.d(TAG,"delete restaurant ${restaurant.restaurantId}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle the failure
                Log.d(TAG,"failed to delete restaurant ${restaurant.restaurantId}")
            }
        })
    }
}