package com.example.whoiscomingalong.ui.theme.screens.allappointmentsscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.whoiscomingalong.R
import com.example.whoiscomingalong.WhoIsComingAlongTheme

@Composable
fun AllAppointmentsScreen(
    navController: NavController,
    allAppointmentsScreenViewModel: AllAppointmentsScreenViewModel = hiltViewModel()
) {
    Log.d("TAG", "AllAppointmentsScreen")

    val appointments by allAppointmentsScreenViewModel.appointments.collectAsState()

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

            Spacer(modifier = Modifier.height(20.dp))

            if (appointments.isEmpty()) {
                Text("No appointments available", color = Color.Gray)
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    appointments.forEach { appointment ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                                .padding(8.dp)
                                .clickable {
                                    navController.navigate("appointment_screen/${appointment.appointmentId}")
                                }
                        ) {
                            Column {
                                Text(appointment.appointmentName, color = Color.DarkGray)
                                Text("Creator: ${appointment.creatorId}", color = Color.DarkGray)
                                Row {
                                  /*  participants.forEach { participant ->
                                        Text("Participant: ${participant.nickName}", color = Color.DarkGray)
                                    }*/
                                    Text("Participant: ", color = Color.DarkGray)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AllAppointmentsScreenPreview() {
    WhoIsComingAlongTheme {
        AllAppointmentsScreen(navController = rememberNavController())
    }
}