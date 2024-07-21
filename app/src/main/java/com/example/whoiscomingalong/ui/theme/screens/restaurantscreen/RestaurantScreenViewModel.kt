package com.example.whoiscomingalong.ui.theme.screens.restaurantscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Restaurant.Restaurant
import com.example.whoiscomingalong.database.Restaurant.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantScreenViewModel @Inject constructor(
    application: Application,
    private val restaurantRepository: RestaurantRepository
) : AndroidViewModel(application) {

    fun getAllRestaurants(): Flow<List<Restaurant>> {
        return restaurantRepository.getAllRestaurants()
    }

    fun getRestaurantById(restaurantId: Int): Flow<Restaurant> {
        return restaurantRepository.getRestaurantById(restaurantId)
    }

    fun insertRestaurant(restaurant: Restaurant) {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantRepository.insert(restaurant)
        }
    }

    fun insertAllRestaurants(vararg restaurants: Restaurant) {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantRepository.insertAll(*restaurants)
        }
    }

    fun deleteRestaurant(restaurant: Restaurant) {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantRepository.delete(restaurant)
        }
    }
}