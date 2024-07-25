package com.example.whoiscomingalong.ui.theme.screens.centralscreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.whoiscomingalong.LogoRed
import com.example.whoiscomingalong.R
import com.example.whoiscomingalong.WhoIsComingAlongTheme

@Composable
fun CentralScreen(
    navController: NavController,
    centralScreenViewModel: CentralScreenViewModel = hiltViewModel()
) {
    Log.d("TAG", "CentralScreen")

    val appointments by centralScreenViewModel.appointments.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_header_transparent),
                contentDescription = "Logo",
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

            // Box for Profile + Settings
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LogoRed, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Profile",
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp)
                )

                FloatingActionButton(
                    onClick = { navController.navigate("profile_screen") },
                    modifier = Modifier
                        .size(40.dp),
                    shape = CircleShape,
                    containerColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Edit Profile",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            // Box for current, open Appointments and Add "+" Button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .border(BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(12.dp)) // Add black border
                    .padding(16.dp)
                    .clickable { navController.navigate("all_appointments_screen") },
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Appointments",
                        fontSize = 25.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    FloatingActionButton(
                        onClick = { navController.navigate("add_new_appointment_screen") },
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                        containerColor = Color.White
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Appointment",
                            tint = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Display Appointments
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    appointments.forEach { appointment ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                                .padding(8.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(appointment.appointmentName, color = Color.DarkGray)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate("group_screen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text(
                        text = "Manage Groups",
                        color = Color.Black,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.Start),
                        textAlign = TextAlign.Left
                    )}

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { navController.navigate("restaurant_screen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text(
                        text = "Manage Restaurants",
                        color = Color.Black,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.Start),
                        textAlign = TextAlign.Left
                    )}
            }
            Spacer(modifier = Modifier.height(20.dp))

            // Logout Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        centralScreenViewModel.logout {
                            navController.navigate("login_screen") {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LogoRed)
                ) {
                    Text(
                        text = "Logout",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    WhoIsComingAlongTheme {
        CentralScreen(navController = rememberNavController())
    }
}