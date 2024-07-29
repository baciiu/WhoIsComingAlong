package com.example.whoiscomingalong.dependencyinjection

import com.example.whoiscomingalong.mocks.MockUserRepository
import com.example.whoiscomingalong.mocks.appointment.MockAppointmentRepository
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

    @Provides
    @Singleton
    fun provideMockAppointmentRepository(): MockAppointmentRepository {
        return MockAppointmentRepository()
    }
}