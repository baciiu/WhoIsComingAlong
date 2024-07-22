package com.example.whoiscomingalong.database.Restaurant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restaurant(
    @PrimaryKey(autoGenerate = true) val restaurantId: Int = 0,
    val restaurantName: String,
    val restaurantAddress: String,
    val restaurantLongitude: Double = 0.0,
    val restaurantLatitude: Double = 0.0
)
