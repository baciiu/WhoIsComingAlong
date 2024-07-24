package com.example.whoiscomingalong

import android.content.Context
import androidx.room.Room
import com.example.whoiscomingalong.database.Appointment.AppointmentDao
import com.example.whoiscomingalong.database.Group.GroupDao
import com.example.whoiscomingalong.database.Restaurant.RestaurantDao
import com.example.whoiscomingalong.database.UserToAppointment.UserToAppointmentDao
import com.example.whoiscomingalong.database.UserToGroup.UserToGroupDao
import com.example.whoiscomingalong.database.Users.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// this object is the Hilt module, it sets up and configures
// the dependencies, so they can be injected wherever needed in the application
// the module contains methods tha provide dependencies
// SingletonComponent means that application-wide only one instance of the
// module is created

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // the @Provides annotation shows Hilt which database and which
    // DAOs to use

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration() // needed if database is changed
            .build()
    }

    @Provides
    fun provideUsersDao(database: AppDatabase): UsersDao {
        return database.usersDao()
    }

    @Provides
    fun provideGroupDao(database: AppDatabase): GroupDao {
        return database.groupDao()
    }

    @Provides
    fun provideUserToGroupDao(database: AppDatabase): UserToGroupDao {
        return database.userToGroupDao()
    }

    @Provides
    fun provideRestaurantDao(database: AppDatabase): RestaurantDao {
        return database.restaurantDao()
    }

    @Provides
    fun provideAppointmentDao(database: AppDatabase): AppointmentDao {
        return database.appointmentDao()
    }

    @Provides
    fun provideUserToAppointmentDao(database: AppDatabase): UserToAppointmentDao {
        return database.userToAppointmentDao()
    }
}