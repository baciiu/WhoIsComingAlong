package com.example.whoiscomingalong.ui.theme.screens.appointmentscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.whoiscomingalong.mocks.appointment.UserToAppointment
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun AppointmentScreen(
    navController: NavController,
    appointmentId: Int,
    viewModel: AppointmentScreenViewModel = hiltViewModel()
) {
    Log.d("TAG", "AppointmentScreen")

    val currentUserId = 1 // Replace with actual current user ID
    val appointments by viewModel.getAllUserToAppointments().collectAsState(initial = emptyList())
    val appointmentDetails by viewModel.appointmentDetails.collectAsState()
    var isComingAlong by remember { mutableStateOf(false) }

    // Load the isComingAlong status for the current user and appointment
    LaunchedEffect(appointments) {
        val userAppointment = appointments.find { it.userId == currentUserId && it.appointmentId == appointmentId }
        isComingAlong = userAppointment?.isComingAlong ?: false
    }

    // Load the appointment details
    LaunchedEffect(appointmentId) {
        viewModel.getAppointmentById(appointmentId)
    }

    val dateFormat = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
    val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState()) // Enable scrolling
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

                Text(text = "Creator: ${appointment.creator.nickName}")

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Date: ${dateFormat.format(appointment.date)}")

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Time: ${timeFormat.format(appointment.hourMinute)}")

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Location: ${appointment.location}")

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Participants:")
                appointment.participants.forEach { participant ->
                    Text(text = participant.nickName)
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Are you coming along?",
                style = TextStyle(
                    fontSize = 20.sp, // Schriftgröße anpassen
                    fontWeight = FontWeight.Bold // Schriftstärke anpassen
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (isComingAlong) {
                    Icon(
                        painter = painterResource(id = R.drawable.true_icon),
                        contentDescription = null,
                        tint = Color.Green,
                        modifier = Modifier.size(24.dp) // Smaller icon size
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.false_icon),
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(24.dp) // Smaller icon size
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    isComingAlong = !isComingAlong
                    viewModel.updateUserToAppointment(
                        UserToAppointment(
                            userId = currentUserId,
                            appointmentId = appointmentId, // Replace with actual appointment ID
                            isComingAlong = isComingAlong
                        )
                    )
                }) {
                    Text(text = "Edit")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
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