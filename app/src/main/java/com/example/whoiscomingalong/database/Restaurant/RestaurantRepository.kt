package com.example.whoiscomingalong.database.Restaurant

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepository @Inject constructor(private val restaurantDao: RestaurantDao) {

    fun getAllRestaurants(): Flow<List<Restaurant>> {
        return restaurantDao.getAll()
    }

    fun getRestaurantById(restaurantId: Int): Flow<Restaurant> {
        return restaurantDao.getById(restaurantId)
    }

    suspend fun insert(restaurant: Restaurant) {
        restaurantDao.insertAll(restaurant)
    }

    suspend fun insertAll(vararg restaurants: Restaurant) {
        restaurantDao.insertAll(*restaurants)
    }

    suspend fun delete(restaurant: Restaurant) {
        restaurantDao.delete(restaurant)
    }
}