package com.example.whoiscomingalong.database.Restaurant

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM Restaurant")
    fun getAll(): Flow<List<Restaurant>>

    @Query("SELECT * FROM Restaurant WHERE restaurantId = :restaurantId")
    fun getById(restaurantId: Int): Flow<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg restaurants: Restaurant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurant: Restaurant)

    @Update
    suspend fun update(restaurant: Restaurant)

    @Delete
    suspend fun delete(restaurant: Restaurant)
}