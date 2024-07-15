package com.example.whoiscomingalong

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application()
// this is necessary to initialize Hilt in the application class
// additionally, MainApplication has to be set in the Android manifest in
// the <application> tag with android:name=".MainApplication"