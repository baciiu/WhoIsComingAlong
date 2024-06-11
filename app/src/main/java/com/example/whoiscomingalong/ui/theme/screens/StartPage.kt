package com.example.whoiscomingalone.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.whoiscomingalong.GreyBeigeLight
import com.example.whoiscomingalong.LogoRed
import com.example.whoiscomingalong.R
import com.example.whoiscomingalong.WhoIsComingAlongTheme

@Composable
fun StartPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Logo oben fixiert
        Image(
            painter = painterResource(id = R.drawable.logo_header),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 16.dp),
            contentScale = ContentScale.FillWidth
        )

        // Box für Profil + Einstellungen
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
                onClick = { navController.navigate("profile_page") },
                modifier = Modifier.size(50.dp),
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

        Spacer(modifier = Modifier.height(16.dp))

        // Box für aktuelle, offene Appointments und Add "+" Button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(GreyBeigeLight, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
                .clickable { navController.navigate("all_appointments_page") },
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Appointments",
                    fontSize = 25.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                FloatingActionButton(
                    onClick = { navController.navigate("add_new_appointment_page") },
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

            // Platzhalter für Appointments
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(3) { // Beispiel für 3 Platzhalter
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text("Appointment Placeholder", color = Color.DarkGray)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // Platzhalter für verbleibenden Raum unten
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Platz für die restlichen Buttons
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate("search_page") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreyBeigeLight)
            ) {
                Text("Search", color = Color.Black, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("history_page") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreyBeigeLight)
            ) {
                Text("Out Of Date - History", color = Color.Black, fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartPagePreview() {
    WhoIsComingAlongTheme {
        StartPage(navController = rememberNavController())
    }
}