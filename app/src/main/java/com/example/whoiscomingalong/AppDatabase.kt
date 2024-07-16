package com.example.whoiscomingalong

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Appointment.AppointmentDao
import com.example.whoiscomingalong.database.Converters
import com.example.whoiscomingalong.database.Group.Group
import com.example.whoiscomingalong.database.Group.GroupDao
import com.example.whoiscomingalong.database.Restaurant.Restaurant
import com.example.whoiscomingalong.database.Restaurant.RestaurantDao
import com.example.whoiscomingalong.database.UserToAppointment.UserToAppointment
import com.example.whoiscomingalong.database.UserToAppointment.UserToAppointmentDao
import com.example.whoiscomingalong.database.UserToGroup.UserToGroup
import com.example.whoiscomingalong.database.UserToGroup.UserToGroupDao
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersDao

@Database(
    entities = [
        Users::class,
        Group::class,
        UserToGroup::class,
        Restaurant::class,
        Appointment::class,
        UserToAppointment::class],
    version = 2
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