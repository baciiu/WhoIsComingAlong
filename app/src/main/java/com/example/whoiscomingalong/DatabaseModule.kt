package com.example.whoiscomingalong

import android.content.Context
import androidx.room.Room
import com.example.whoiscomingalong.Database.Users.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// this object is the Hilt module, it sets up and configures
// the dependencies, so they can be injected wherever needed in the application
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUsersDao(database: AppDatabase): UsersDao {
        return database.usersDao()
    }
}