package com.example.whoiscomingalong.ui.theme.screens.addnewappointmentscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.whoiscomingalong.LogoRed
import com.example.whoiscomingalong.R
import com.example.whoiscomingalong.WhoIsComingAlongTheme
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun AddNewAppointmentScreen(
    navController: NavController,
    addNewAppointmentScreenViewModel: AddNewAppointmentScreenViewModel = hiltViewModel()
) {
    Log.d("TAG", "AppointmentScreen")

    var appointmentName by remember { mutableStateOf("") }
    var selectedGroup by remember { mutableStateOf<Int?>(null) }
    var selectedRestaurant by remember { mutableStateOf<Int?>(null) }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    val allGroups by addNewAppointmentScreenViewModel.getAllGroups().collectAsState(initial = emptyList())
    val allRestaurants by addNewAppointmentScreenViewModel.getAllRestaurants().collectAsState(initial = emptyList())

    val coroutineScope = rememberCoroutineScope()

    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
    val timeFormat = remember { SimpleDateFormat("HH:mm", Locale.getDefault()) }

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

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = appointmentName,
                onValueChange = { appointmentName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("Appointment Name") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Select Group", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(10.dp))

            allGroups.forEach { group ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = selectedGroup == group.groupId,
                        onClick = { selectedGroup = group.groupId }
                    )
                    Text(text = group.groupName)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Select Restaurant", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(10.dp))

            allRestaurants.forEach { restaurant ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = selectedRestaurant == restaurant.restaurantId,
                        onClick = { selectedRestaurant = restaurant.restaurantId }
                    )
                    Text(text = restaurant.restaurantName)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("Date (yyyy-MM-dd)") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = time,
                onValueChange = { time = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("Time (HH:mm)") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        if (selectedGroup != null && selectedRestaurant != null) {
                            try {
                                val parsedDate = dateFormat.parse(date)
                                val parsedTime = timeFormat.parse(time)
                                if (parsedDate != null && parsedTime != null) {
                                    addNewAppointmentScreenViewModel.insertAppointment(
                                        appointmentName = appointmentName,
                                        groupId = selectedGroup!!,
                                        restaurantId = selectedRestaurant!!,
                                        date = parsedDate,
                                        hourMinute = parsedTime
                                    )
                                    navController.navigate("start_screen")
                                }
                            } catch (e: Exception) {
                                Log.e("TAG", "Error parsing date/time: ${e.message}")
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LogoRed)
            ) {
                Text(
                    text = "CREATE APPOINTMENT",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddNewAppointmentScreenPreview() {
    WhoIsComingAlongTheme {
        AddNewAppointmentScreen(navController = rememberNavController())
    }
}