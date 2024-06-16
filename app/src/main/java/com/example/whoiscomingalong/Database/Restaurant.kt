package com.example.whoiscomingalong

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restaurant(
    @PrimaryKey val restaurantId: Int,
    val restaurantName: String,
    val restaurantLongitude: Double,
    val restaurantLatitude: Double
)