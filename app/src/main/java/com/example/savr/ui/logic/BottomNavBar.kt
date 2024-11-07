package com.example.savr.ui.logic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.savr.R

@Composable
fun BottomNavBar(
    navController: NavController,
    selectedRoute: String
) { // New parameter for the selected icon
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White) // Set the background to white
            .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)) // Clip to the rounded shape
            .background(
                Color(0xFFFFF4EC), shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
            ) // White background with rounded corners
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 25.dp) // Padding for the Row
        ) {
            // Icon Buttons
            IconButton(
                onClick = { navController.navigate("home/{email}/{password}") },// Navigate to Home on click
                modifier = Modifier.padding(end = 20.dp)
            ) {
                val icon = if (selectedRoute == "home/{email}/{password}") {
                    painterResource(id = R.drawable.selected_home)
                } else {
                    painterResource(id = R.drawable.home)
                }
                Image(
                    painter = icon, // Load the home icon
                    contentDescription = "Home",
                    modifier = Modifier.size(30.dp) // Use size for uniformity
                )
            }

            IconButton(
                onClick = { navController.navigate("analysis") },
                modifier = Modifier.padding(end = 20.dp)
            ) {
                val icon = if (selectedRoute == "analysis") {
                    painterResource(id = R.drawable.selected_analysis) // Selected icon
                } else {
                    painterResource(id = R.drawable.analysis) // Regular icon
                }
                Image(
                    painter = icon, // Load the analysis icon
                    contentDescription = "Analysis",
                    modifier = Modifier.size(30.dp) // Use size for uniformity
                )
            }

            IconButton(
                onClick = { navController.navigate("transactions") },
                modifier = Modifier.padding(end = 25.dp)
            ) {
                val icon = if (selectedRoute == "transactions") {
                    painterResource(id = R.drawable.selected_transactions) // Selected icon
                } else {
                    painterResource(id = R.drawable.transactions) // Regular icon
                }
                Image(
                    painter = icon, // Load the transactions icon
                    contentDescription = "Transactions",
                    modifier = Modifier.size(30.dp) // Use size for uniformity
                )
            }

            IconButton(
                onClick = { navController.navigate("categories") },
                modifier = Modifier.padding(end = 28.dp)
            ) {
                val icon = if (selectedRoute == "categories") {
                    painterResource(id = R.drawable.selected_category) // Selected icon
                } else {
                    painterResource(id = R.drawable.category) // Regular icon
                }
                Image(
                    painter = icon, // Load the category icon
                    contentDescription = "Categories",
                    modifier = Modifier.size(30.dp) // Use size for uniformity
                )
            }

            IconButton(
                onClick = { navController.navigate("profile") }, modifier = Modifier
            ) {
                val icon = if (selectedRoute == "profile") {
                    painterResource(id = R.drawable.selected_profile) // Selected icon
                } else {
                    painterResource(id = R.drawable.profile) // Regular icon
                }
                Image(
                    painter = icon, // Load the profile icon
                    contentDescription = "Profile",
                    modifier = Modifier.size(30.dp) // Use size for uniformity
                )
            }
        }
    }
}
