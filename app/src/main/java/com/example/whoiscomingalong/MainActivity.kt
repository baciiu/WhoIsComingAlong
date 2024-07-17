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
import com.example.whoiscomingalong.ui.theme.screens.centralscreen.CentralScreen
import com.example.whoiscomingalong.ui.theme.components.AddNewAppointmentScreen
import com.example.whoiscomingalong.ui.theme.screens.allappointmentsscreen.AllAppointmentsScreen
import com.example.whoiscomingalong.ui.theme.screens.appointmentscreen.AppointmentScreen
import com.example.whoiscomingalong.ui.theme.screens.historyscreen.HistoryScreen
import com.example.whoiscomingalong.ui.theme.screens.loginscreen.LoginScreen
import com.example.whoiscomingalong.ui.theme.screens.profilescreen.ProfileScreen
import com.example.whoiscomingalong.ui.theme.screens.searchscreen.SearchScreen
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
        // here is how to call the database; delete or change if necessary (todo)
        /*
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "appDatabase"
        ).build() */

        // appDatabase.usersDao().insertAll()

        // then with appDatabase it is possible to access the tables (remarks: it
        // might be necessary to modify or add daos for proper usage of the app!)
        // Create multiple User instances

        /* val user1 = Users(
            userId = 1,
            firstName = "Klemens",
            lastName = "Vospernik",
            nickName = "Klemens",
            dateOfBirth = Date(1973, 6, 19),
            email = "klemens@example.com",
            company = "it",
            department = "development",
            password = "securepassword1"
        )
        val user2 = Users(
            userId = 1,
            firstName = "Angie",
            lastName = "Ducks",
            nickName = "Angie",
            dateOfBirth = Date(1998, 1, 1),
            email = "angie@example.com",
            company = "it",
            department = "development",
            password = "securepassword2"
        )
        // Example: call insertAll with one or more Users instances:
        appDatabase.usersDao().insertAll(user1, user2) */

    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login_page") {
        composable("start_page") { CentralScreen(navController) }
        composable("profile_page") { ProfileScreen(navController) }
        composable("login_page") { LoginScreen(navController) }
        composable("signup_page") { SignUpScreen(navController) }
        composable("appointment_page") { AppointmentScreen(navController) }
        composable("search_page") { SearchScreen(navController) }
        composable("history_page") { HistoryScreen(navController) }
        composable("add_new_appointment_page") { AddNewAppointmentScreen(navController) }
        composable("all_appointments_page") { AllAppointmentsScreen(navController) }
    }
}
