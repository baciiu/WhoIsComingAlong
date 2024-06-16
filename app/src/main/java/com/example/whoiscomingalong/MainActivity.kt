package com.example.whoiscomingalong

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.whoiscomingalone.ui.theme.screens.StartPage
import com.example.whoiscomingalong.ui.theme.components.AddNewAppointmentPage
import com.example.whoiscomingalong.ui.theme.screens.AllAppointmentsPage
import com.example.whoiscomingalong.ui.theme.screens.AppointmentPage
import com.example.whoiscomingalong.ui.theme.screens.HistoryPage
import com.example.whoiscomingalong.ui.theme.screens.LoginScreen
import com.example.whoiscomingalong.ui.theme.screens.ProfilePage
import com.example.whoiscomingalong.ui.theme.screens.SearchPage
import com.example.whoiscomingalong.ui.theme.screens.SignUpScreen


class MainActivity : ComponentActivity() {
    lateinit var appDatabase: AppDatabase

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the app to draw edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            WhoIsComingAlongTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavigationComponent(navController)
                }
            }
        }
        // here is how to call the database; delete or change if necessary (todo)
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "appDatabase"
        ).build()
        // then with appDatabase it is possible to access the tables (remarks: it
        // might be necessary to modify or add daos for proper usage of the app!)
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login_page") {
        composable("start_page") { StartPage(navController) }
        composable("profile_page") { ProfilePage(navController) }
        composable("login_page") { LoginScreen(navController)}
        composable("signup_page") { SignUpScreen(navController)}
        composable("appointment_page") { AppointmentPage(navController) }
        composable("search_page") { SearchPage(navController) }
        composable("history_page") { HistoryPage(navController) }
        composable("add_new_appointment_page") { AddNewAppointmentPage(navController) }
        composable("all_appointments_page") { AllAppointmentsPage(navController) }
    }
}
