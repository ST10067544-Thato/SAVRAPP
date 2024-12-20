package com.example.savr.ui.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.savr.R
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.ScreenTopSection
import com.example.savr.ui.screens.category.LogoutDialog

@Composable
fun Profile(navController: NavController) {
    var showLogoutDialog by remember { mutableStateOf(false) } // State for dialog visibility
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF8D3C)) // Base orange background
    ) {
        // Main content area with the notification bar and text
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 15.dp)
                .padding(horizontal = 36.dp) // Horizontal padding for the content
        ) {
            ScreenTopSection(
                navController = navController,
                title = "Profile",
                onBack = { navController.popBackStack() }) // Add this line
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Takes remaining space
                .background(Color(0xFFFFFFFF), RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .verticalScroll(rememberScrollState()) // Enables scrolling
                .padding(top = 20.dp) // Padding to separate from the orange section
        ) { // Expanded Box for LazyColumn and Button
            // Curved white layered page for categories
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White, RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                    )
            ) {
                // Profile Picture Placeholder
                Image(
                    painter = painterResource(id = R.drawable.profile_placeholder), // Replace with your placeholder image
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp) // Adjust size as needed
                        .clip(CircleShape) // Make it circular
                        .align(Alignment.CenterHorizontally) // Center horizontally
                        .padding(bottom = 8.dp) // Add spacing below the image
                )
                Text(
                    "John Smith",
                    color = Color(0xFF0E3E3E),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally) // Center horizontally
                        .padding(top = 8.dp, bottom = 55.dp)
                )

                // Profile items
                ProfileItem(
                    "Edit Profile",
                    R.drawable.profile_settings
                ) { navController.navigate("edit_profile") }
                ProfileItem(
                    "Security",
                    R.drawable.security
                ) { navController.navigate("security_settings") }
                ProfileItem(
                    "Settings",
                    R.drawable.settings
                ) { navController.navigate("notification_settings") }
                ProfileItem("Logout", R.drawable.logout) {
                    showLogoutDialog = true
                } // Show dialog on Logout click
            }
        }
        BottomNavBar(navController = navController, selectedRoute = "profile")
    }
    // Logout Dialog
    if (showLogoutDialog) {
        LogoutDialog(onDismiss = { showLogoutDialog = false }, onLogout = {
            // Handle logout logic here
            showLogoutDialog = false
        })
    }
}

@Composable
fun ProfileItem(
    text: String, iconRes: Int, onClick: () -> Unit
) { // Added onClick parameter, removed color
    OutlinedButton(
        onClick = onClick, // Pass onClick lambda to OutlinedButton
        border = BorderStroke(0.dp, Color.Transparent),
        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
        contentPadding = PaddingValues(),
        modifier = Modifier
            .padding(bottom = 24.dp, start = 38.dp, end = 38.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Icon (using Image composable)
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(53.dp), // Adjust icon size as needed
            )
            Text(
                text = text,
                color = Color(0xFF093030),
                fontSize = 15.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()// Create a NavController for preview
    Profile(navController)
}