package com.example.whoiscomingalong.ui.theme.screens.loginscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.whoiscomingalong.LogoRed
import com.example.whoiscomingalong.R
import com.example.whoiscomingalong.WhoIsComingAlongTheme

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginScreenViewModel = hiltViewModel()) {
    Log.d("LoginScreen", "LoginScreen displayed")

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
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
                value = email.value,
                onValueChange = { email.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                label = { Text("Username") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("Password") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Button(
                onClick = {
                    Log.d("LoginScreen", "Login button clicked")
                    viewModel.authenticateUser(email.value, password.value) { user ->
                        if (user != null) {
                            Log.d("LoginScreen", "User authenticated successfully")
                            navController.navigate("start_screen") // Navigate to the central screen
                        } else {
                            viewModel.getUserByNickName(email.value) { existingUser ->
                                errorMessage = if (existingUser != null) {
                                    Log.d("LoginScreen", "Incorrect password")
                                    "Incorrect password"
                                } else {
                                    Log.d("LoginScreen", "User does not exist")
                                    "User does not exist"
                                }
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
                    text = "LOGIN",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "have no account?",
                    color = Color.Black,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Sign Up",
                    color = LogoRed,
                    fontSize = 20.sp,
                    modifier = Modifier.clickable { navController.navigate("signup_screen") }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    WhoIsComingAlongTheme {
        LoginScreen(navController = rememberNavController())
    }
}