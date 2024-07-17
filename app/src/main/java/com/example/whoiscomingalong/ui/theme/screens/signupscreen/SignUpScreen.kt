package com.example.whoiscomingalong.ui.theme.screens.signupscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.LogoRed
import com.example.whoiscomingalong.R
import com.example.whoiscomingalong.WhoIsComingAlongTheme
import java.util.Date

@Composable
fun SignUpScreen(navController: NavHostController) { //}, userViewModel: UserViewModel = hiltViewModel()) {
    Log.d("TAG", "SignUpScreen")

    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val dateOfBirth = remember { mutableStateOf("") }
    val company = remember { mutableStateOf("") }
    val department = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val signUpScreenViewModel: SignUpScreenViewModel = hiltViewModel()

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
                value = firstName.value,
                onValueChange = { firstName.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("First Name") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            OutlinedTextField(
                value = lastName.value,
                onValueChange = { lastName.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("Last Name") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            OutlinedTextField(
                value = dateOfBirth.value,
                onValueChange = { dateOfBirth.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("Date of Birth") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            OutlinedTextField(
                value = company.value,
                onValueChange = { company.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("Company") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            OutlinedTextField(
                value = department.value,
                onValueChange = { department.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("Department") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("Email") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
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

            Button(
                onClick = {
                    val user = Users(
                        userId = 0, // Assuming userId is auto-generated by the database
                        firstName = firstName.value,
                        lastName = lastName.value,
                        nickName = username.value,
                        dateOfBirth = Date(), // This should be parsed from dateOfBirth.value
                        email = email.value,
                        password = password.value,
                        company = company.value,
                        department = department.value
                    )
                    signUpScreenViewModel.insertUser(user)
                    navController.navigate("start_page") // Navigate to the StartPage
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LogoRed)
            ) {
                Text(
                    text = "SIGN UP",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "have an account?",
                    color = Color.Black,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Sign In",
                    color = LogoRed,
                    fontSize = 20.sp,
                    modifier = Modifier.clickable { navController.navigate("login_page") }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    WhoIsComingAlongTheme {
        SignUpScreen(navController = rememberNavController())
    }
}
