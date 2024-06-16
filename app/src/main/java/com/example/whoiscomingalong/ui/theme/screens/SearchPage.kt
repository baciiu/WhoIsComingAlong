package com.example.whoiscomingalong.ui.theme.screens

// File: ui/SearchPage.kt

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.whoiscomingalong.AppBarWithBackButton
import com.example.whoiscomingalong.WhoIsComingAlongTheme

@Composable
fun SearchPage(navController: NavController) {
    Scaffold(
        topBar = { AppBarWithBackButton(navController, "Search") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Search Page",
                fontSize = 30.sp,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPagePreview() {
    WhoIsComingAlongTheme {
        SearchPage(navController = rememberNavController())
    }
}