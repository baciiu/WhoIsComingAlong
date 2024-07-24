package com.example.whoiscomingalong

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// @HiltAndroidApp is a necessary annotation to initialize Hilt in the application class
// additionally, MainApplication has to be set in the Android manifest in
// the <application> tag with android:name=".MainApplication"

@HiltAndroidApp
class MainApplication : Application()
