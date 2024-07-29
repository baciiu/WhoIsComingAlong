package com.example.whoiscomingalong.dependencyinjection

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RoomRepository

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockRepository