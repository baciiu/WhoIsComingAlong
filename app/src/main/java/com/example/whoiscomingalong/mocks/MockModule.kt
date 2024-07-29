package com.example.whoiscomingalong.di

import com.example.whoiscomingalong.mocks.MockUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MockModule {

    @Provides
    @Singleton
    fun provideMockUserRepository(): MockUserRepository {
        return MockUserRepository()
    }
}