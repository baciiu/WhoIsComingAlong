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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whoiscomingalone.ui.theme.screens.StartPage
import com.example.whoiscomingalong.ui.theme.components.AddNewAppointmentPage
import com.example.whoiscomingalong.ui.theme.screens.AllAppointmentsPage
import com.example.whoiscomingalong.ui.theme.screens.AppointmentPage
import com.example.whoiscomingalong.ui.theme.screens.HistoryPage
import com.example.whoiscomingalong.ui.theme.screens.ProfilePage
import com.example.whoiscomingalong.ui.theme.screens.SearchPage


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhoIsComingAlongTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavigationComponent(navController)
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start_page") {
        composable("start_page") { StartPage(navController) }
        composable("profile_page") { ProfilePage(navController) }
        composable("appointment_page") { AppointmentPage(navController) }
        composable("search_page") { SearchPage(navController) }
        composable("history_page") { HistoryPage(navController) }
        composable("add_new_appointment_page") { AddNewAppointmentPage(navController) }
        composable("all_appointments_page") { AllAppointmentsPage(navController) }
    }
}
