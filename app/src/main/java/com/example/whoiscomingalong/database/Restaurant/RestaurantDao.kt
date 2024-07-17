package com.example.whoiscomingalong.database.Restaurant

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM Restaurant")
    fun getAll(): Flow<List<Restaurant>>

    @Insert
    fun insertAll(vararg restaurants: Restaurant)

    @Delete
    fun delete(restaurant: Restaurant)
}