package com.example.whoiscomingalong.ui.theme.screens.profilescreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.whoiscomingalong.LogoRed
import com.example.whoiscomingalong.R
import com.example.whoiscomingalong.WhoIsComingAlongTheme
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ProfileScreen(
    navController: NavController,
    profileScreenViewModel: ProfileScreenViewModel = viewModel()
) {
    Log.d("TAG", "ProfileScreen")

    var isEditing by remember { mutableStateOf(false) }
    val user by profileScreenViewModel.getUserById(1).collectAsState(initial = null) // Assuming 1 is the userId of the current user

    val dateFormatter = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
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

                user?.let {
                    ProfileTextField(
                        label = "First Name",
                        value = it.firstName,
                        isEditing = isEditing
                    ) { newValue ->
                        profileScreenViewModel.updateUser(it.copy(firstName = newValue))
                    }
                    ProfileTextField(
                        label = "Last Name",
                        value = it.lastName,
                        isEditing = isEditing
                    ) { newValue ->
                        profileScreenViewModel.updateUser(it.copy(lastName = newValue))
                    }
                    ProfileTextField(
                        label = "Nick Name",
                        value = it.nickName,
                        isEditing = isEditing
                    ) { newValue ->
                        profileScreenViewModel.updateUser(it.copy(nickName = newValue))
                    }
                    ProfileTextField(
                        label = "Date of Birth",
                        value = it.dateOfBirth,
                        isEditing = isEditing
                    ) { newValue ->
                        try {
                            val parsedDate = dateFormatter.parse(newValue)
                            if (parsedDate != null) {
                                profileScreenViewModel.updateUser(it.copy(dateOfBirth = dateFormatter.format(parsedDate)))
                            }
                        } catch (e: Exception) {
                            Log.e("ProfileScreen", "Failed to parse date: ${e.message}")
                        }
                    }
                    ProfileTextField(
                        label = "Email",
                        value = it.email,
                        isEditing = isEditing
                    ) { newValue ->
                        profileScreenViewModel.updateUser(it.copy(email = newValue))
                    }
                    ProfileTextField(
                        label = "Company",
                        value = it.company,
                        isEditing = isEditing
                    ) { newValue ->
                        profileScreenViewModel.updateUser(it.copy(company = newValue))
                    }
                    ProfileTextField(
                        label = "Department",
                        value = it.department,
                        isEditing = isEditing
                    ) { newValue ->
                        profileScreenViewModel.updateUser(it.copy(department = newValue))
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
                Button(
                    onClick = {
                        if (isEditing) {
                            isEditing = false
                            navController.navigate("start_screen") // Navigate back to the start screen after saving changes
                        } else {
                            isEditing = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LogoRed),
                    contentPadding = PaddingValues()
                ) {
                    Text(
                        text = if (isEditing) "Save Changes" else "Edit Profile",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileTextField(label: String, value: String, isEditing: Boolean, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp, color = Color.Gray))
        if (isEditing) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )
        } else {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp, color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    WhoIsComingAlongTheme {
        ProfileScreen(navController = rememberNavController())
    }
}