package com.example.whoiscomingalong.ui.theme.screens.appointmentscreen

// File: ui/AppointmentScreen.kt

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

@Composable
fun AppointmentScreen(
    navController: NavController,
    viewModel: AppointmentScreenViewModel = hiltViewModel()
) {
    Log.d("TAG", "AppointmentScreen")

    val currentUserId = 1 // Replace with actual current user ID
    val appointments by viewModel.getAllUserToAppointments().collectAsState(initial = emptyList())
    var isComingAlong by remember { mutableStateOf(false) }

    // Load the isComingAlong status for the current user and appointment
    LaunchedEffect(appointments) {
        val userAppointment = appointments.find { it.userId == currentUserId }
        isComingAlong = userAppointment?.isComingAlong ?: false
    }
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
                        tint = Color.Green
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.false_icon),
                        contentDescription = null,
                        tint = Color.Red
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    isComingAlong = !isComingAlong
                    viewModel.updateUserToAppointment(
                        UserToAppointment(
                            userId = currentUserId,
                            appointmentId = 1, // Replace with actual appointment ID
                            isComingAlong = isComingAlong
                        )
                    )
                }) {
                    Text(text = "Edit")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppointmentScreenPreview() {
    WhoIsComingAlongTheme {
        AppointmentScreen(navController = rememberNavController())
    }
}