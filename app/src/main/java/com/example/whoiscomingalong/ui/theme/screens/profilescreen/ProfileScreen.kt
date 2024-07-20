// File: ui/ProfileScreen.kt

package com.example.whoiscomingalong.ui.theme.screens.profilescreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.whoiscomingalong.R
import com.example.whoiscomingalong.WhoIsComingAlongTheme
import com.example.whoiscomingalong.database.Users.Users

@Composable
fun ProfileScreen(
    navController: NavController,
    profileScreenViewModel: ProfileScreenViewModel = viewModel()
) {
    Log.d("TAG", "ProfileScreen")

    var showUsers by remember { mutableStateOf(false) }
    val users by profileScreenViewModel.getAllUsers().collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            // .verticalScroll(rememberScrollState()) // Enable scrolling
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

                Spacer(modifier = Modifier.height(5.dp))

                Button(onClick = { showUsers = true }) {
                    Text(text = "Show all Users")
                }
            }

            if (showUsers) {
                items(users) { user ->
                    UserRow(user = user,
                        onDeleteClick = {
                            profileScreenViewModel.deleteUser(it)
                        })
                }

            }
        }
    }
}


@Composable
fun UserRow(user: Users, onDeleteClick: (Users) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "${user.firstName} ${user.lastName}")
            Text(text = user.nickName)
        }
        IconButton(onClick = { onDeleteClick(user) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete User")
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

