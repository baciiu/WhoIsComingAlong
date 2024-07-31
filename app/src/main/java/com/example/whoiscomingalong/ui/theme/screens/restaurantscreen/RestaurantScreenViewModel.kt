package com.example.whoiscomingalong.ui.theme.screens.restaurantscreen

import android.app.Application
import android.util.Log
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

    private val TAG = "RestaurantValidation"

    fun getAllRestaurants(): Flow<List<Restaurant>> {
        return restaurantRepository.getAllRestaurants()
    }

    fun getRestaurantById(restaurantId: Int): Flow<Restaurant> {
        return restaurantRepository.getRestaurantById(restaurantId)
    }

    fun insertRestaurant(restaurant: Restaurant) {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantRepository.insert(restaurant)
            Log.d(TAG, "Restaurant inserted successfully: $restaurant")
        }
    }

    fun insertAllRestaurants(vararg restaurants: Restaurant) {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantRepository.insertAll(*restaurants)
            Log.d(TAG, "Restaurants inserted successfully: ${restaurants.joinToString()}")
        }
    }

    fun deleteRestaurant(restaurant: Restaurant) {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantRepository.delete(restaurant)
            Log.d(TAG, "Restaurant deleted successfully: $restaurant")
        }
    }

    private fun validateRestaurant(restaurant: Restaurant): Boolean {
        var isValid = true

        if (restaurant.restaurantName.isBlank()) {
            Log.e(TAG, "Restaurant name is blank")
            isValid = false
        } else {
            Log.d(TAG, "Restaurant name is valid")
        }

        restaurant.restaurantLongitude?.let {
            if (it !in -180.0..180.0) {
                Log.e(TAG, "Longitude is out of range: $it")
                isValid = false
            } else {
                Log.d(TAG, "Longitude is valid: $it")
            }
        }

        restaurant.restaurantLatitude?.let {
            if (it !in -90.0..90.0) {
                Log.e(TAG, "Latitude is out of range: $it")
                isValid = false
            } else {
                Log.d(TAG, "Latitude is valid: $it")
            }
        }

        return isValid
    }
}
