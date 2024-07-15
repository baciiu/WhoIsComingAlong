package com.example.whoiscomingalong

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.whoiscomingalong.Database.Appointment.Appointment
import com.example.whoiscomingalong.Database.Appointment.AppointmentDao
import com.example.whoiscomingalong.Database.Converters
import com.example.whoiscomingalong.Database.Group.Group
import com.example.whoiscomingalong.Database.Group.GroupDao
import com.example.whoiscomingalong.Database.Restaurant.Restaurant
import com.example.whoiscomingalong.Database.Restaurant.RestaurantDao
import com.example.whoiscomingalong.Database.UserToAppointment.UserToAppointment
import com.example.whoiscomingalong.Database.UserToAppointment.UserToAppointmentDao
import com.example.whoiscomingalong.Database.UserToGroup.UserToGroup
import com.example.whoiscomingalong.Database.UserToGroup.UserToGroupDao
import com.example.whoiscomingalong.Database.Users.Users
import com.example.whoiscomingalong.Database.Users.UsersDao

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