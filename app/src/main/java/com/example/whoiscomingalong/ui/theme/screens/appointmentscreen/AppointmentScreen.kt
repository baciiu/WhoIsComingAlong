package com.example.whoiscomingalong.ui.theme.screens.appointmentscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.whoiscomingalong.R
import com.example.whoiscomingalong.WhoIsComingAlongTheme
import com.example.whoiscomingalong.database.Appointment.Appointment
import com.example.whoiscomingalong.database.Users.Users
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun AppointmentScreen(
    navController: NavController,
    appointmentId: Int,
    viewModel: AppointmentScreenViewModel = hiltViewModel()
) {
    Log.d("TAG", "AppointmentScreen")

    val appointmentDetails by viewModel.appointmentDetails.collectAsState()
    var creator by remember { mutableStateOf<Users?>(null) }

    // Load the appointment details
    LaunchedEffect(appointmentId) {
        viewModel.getAppointmentById(appointmentId)
    }

    appointmentDetails?.let { appointment ->
        LaunchedEffect(appointment.groupId) {
            viewModel.getUserById(appointment.groupId).collect { user ->
                creator = user
            }
        }
    }

    val dateFormat = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
    val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_header_transparent),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
            )

            Spacer(modifier = Modifier.height(0.dp))

            HorizontalDivider(
                modifier = Modifier.width(40.dp),
                thickness = 1.dp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(5.dp))

            appointmentDetails?.let { appointment ->
                Text(
                    text = appointment.appointmentName,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                creator?.let {
                    Text(text = "Creator: ${it.nickName}")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Date: ${dateFormat.format(appointment.date)}")

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Time: ${timeFormat.format(appointment.hourMinute)}")

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppointmentScreenPreview() {
    WhoIsComingAlongTheme {
        AppointmentScreen(navController = rememberNavController(), appointmentId = 1)
    }
}