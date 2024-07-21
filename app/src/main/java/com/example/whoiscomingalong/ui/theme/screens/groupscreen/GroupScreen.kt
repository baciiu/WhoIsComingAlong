package com.example.whoiscomingalong.ui.theme.screens.groupscreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.whoiscomingalong.LogoRed
import com.example.whoiscomingalong.R
import com.example.whoiscomingalong.WhoIsComingAlongTheme
import com.example.whoiscomingalong.database.Group.Group
import com.example.whoiscomingalong.database.Users.Users
import kotlinx.coroutines.launch

@Composable
fun GroupScreen(
    navController: NavController,
    groupScreenViewModel: GroupScreenViewModel = hiltViewModel()
) {
    Log.d("TAG", "GroupScreen")

    var groupName by remember { mutableStateOf("") }
    var selectedUsers by remember { mutableStateOf(listOf<Users>()) }
    val allUsers by groupScreenViewModel.getAllUsers().collectAsState(initial = emptyList())
    val allGroups by groupScreenViewModel.getAllGroups().collectAsState(initial = emptyList())

    val coroutineScope = rememberCoroutineScope()

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

            Spacer(modifier = Modifier.height(20.dp))

            // Create new group section
            Text(
                text = "Create New Group",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            OutlinedTextField(
                value = groupName,
                onValueChange = { groupName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                label = { Text("Group Name") },
                textStyle = TextStyle(color = Color.Black),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Select Users to Add to Group", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(10.dp))

            allUsers.forEach { user ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = selectedUsers.contains(user),
                        onCheckedChange = {
                            if (it) {
                                selectedUsers = selectedUsers + user
                            } else {
                                selectedUsers = selectedUsers - user
                            }
                        }
                    )
                    Text(text = "${user.firstName} ${user.lastName}")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        val newGroup = Group(groupName = groupName)
                        val groupId = groupScreenViewModel.insertGroup(newGroup).toInt()
                        selectedUsers.forEach { user ->
                            groupScreenViewModel.addUserToGroup(user.userId, groupId)
                        }
                        navController.navigate("start_screen")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LogoRed)
            ) {
                Text(
                    text = "CREATE GROUP",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Display existing groups
            Text(
                text = "Existing Groups",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            allGroups.forEach { group ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        text = group.groupName,
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupScreenPreview() {
    WhoIsComingAlongTheme {
        GroupScreen(navController = rememberNavController())
    }
}
