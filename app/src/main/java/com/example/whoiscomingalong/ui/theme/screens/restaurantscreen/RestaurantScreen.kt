package com.example.whoiscomingalong.ui.theme.screens.restaurantscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.whoiscomingalong.database.Restaurant.Restaurant
import kotlinx.coroutines.launch

@Composable
fun RestaurantScreen(
    navController: NavController,
    restaurantScreenViewModel: RestaurantScreenViewModel = hiltViewModel()
) {
    Log.d("TAG", "RestaurantScreen")

    var restaurantName by remember { mutableStateOf("") }
    var restaurantAddress by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

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

            OutlinedTextField(
                value = restaurantName,
                onValueChange = { restaurantName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("Restaurant Name") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = longitude,
                onValueChange = { longitude = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("Longitude (optional)") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = latitude,
                onValueChange = { latitude = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("Latitude (optional)") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        val newRestaurant = Restaurant(
                            restaurantName = restaurantName,
                            restaurantAddress = restaurantAddress,
                            restaurantLongitude = longitude.toDoubleOrNull() ?: 0.0,
                            restaurantLatitude = latitude.toDoubleOrNull() ?: 0.0
                        )
                        restaurantScreenViewModel.insertRestaurant(newRestaurant)
                        navController.navigate("start_screen")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LogoRed)
            ) {
                Text(
                    text = "CREATE RESTAURANT",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantScreenPreview() {
    WhoIsComingAlongTheme {
        RestaurantScreen(navController = rememberNavController())
    }
}
