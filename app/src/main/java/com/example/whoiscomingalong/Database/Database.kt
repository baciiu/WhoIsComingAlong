package com.example.whoiscomingalong

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        Users::class,
        Group::class,
        UserToGroup::class,
        Restaurant::class,
        Appointment::class,
        UserToAppointment::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun groupDao(): GroupDao
    abstract fun userToGroupDao(): UserToGroupDao
    abstract fun restaurantDao(): RestaurantDao
    abstract fun appointmentDao(): AppointmentDao
    abstract fun userToAppointmentDao(): UserToAppointmentDao
}