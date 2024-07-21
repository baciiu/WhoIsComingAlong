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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whoiscomingalong.ui.theme.screens.centralscreen.CentralScreen
import com.example.whoiscomingalong.ui.theme.components.AddNewAppointmentScreen
import com.example.whoiscomingalong.ui.theme.screens.allappointmentsscreen.AllAppointmentsScreen
import com.example.whoiscomingalong.ui.theme.screens.appointmentscreen.AppointmentScreen
import com.example.whoiscomingalong.ui.theme.screens.loginscreen.LoginScreen
import com.example.whoiscomingalong.ui.theme.screens.profilescreen.ProfileScreen
import com.example.whoiscomingalong.ui.theme.screens.profilescreen.ProfileScreenViewModel
import com.example.whoiscomingalong.ui.theme.screens.groupscreen.GroupScreen
import com.example.whoiscomingalong.ui.theme.screens.restaurantscreen.RestaurantScreen
import com.example.whoiscomingalong.ui.theme.screens.signupscreen.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
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
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login_screen") {
        composable("start_screen") { CentralScreen(navController) }
        composable("profile_screen") {
            val profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
            ProfileScreen(navController = navController, profileScreenViewModel = profileScreenViewModel)
        }
        composable("login_screen") { LoginScreen(navController) }
        composable("signup_screen") { SignUpScreen(navController) }
        composable("appointment_screen") { AppointmentScreen(navController) }
        composable("group_screen") { GroupScreen(navController) }
        composable("restaurant_screen") { RestaurantScreen(navController) }
        composable("add_new_appointment_screen") { AddNewAppointmentScreen(navController) }
        composable("all_appointments_screen") { AllAppointmentsScreen(navController) }
    }
}