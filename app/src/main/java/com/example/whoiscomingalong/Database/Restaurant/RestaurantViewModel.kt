package com.example.whoiscomingalong.Database.Restaurant

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.whoiscomingalong.AppDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RestaurantViewModel(application: Application) : AndroidViewModel(application) {
    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).build()

    val allRestaurants: StateFlow<List<Restaurant>> = db.restaurantDao().getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertRestaurant(restaurant: Restaurant) {
        viewModelScope.launch {
            db.restaurantDao().insertAll(restaurant)
        }
    }
}