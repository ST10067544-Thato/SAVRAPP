package com.example.savr.ui.screens.profile.security

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.savr.R
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.CustomNotificationBar
import com.example.savr.ui.logic.ScreenTopSection

@Composable
fun Security(navController: NavController) {
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
            ScreenTopSection(navController = navController, title = "Security Settings", onBack = { navController.popBackStack() }) // Add this line
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
                    .padding(horizontal = 36.dp) // Add horizontal padding
            ) {
                // "Security" heading
                Text(
                    text = "Security",
                    color = Color(0xFF093030),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp, bottom = 30.dp)
                )

                // "Change password" clickable text with icon
                OutlinedButton(
                    onClick = { navController.navigate("change_password") },
                    border = BorderStroke(0.dp, Color.Transparent),
                    colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween, // This ensures spacing
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth() // This makes the Row fill the width
                    ) {
                        Text(
                            text = "Change password", color = Color(0xFF093030), fontSize = 15.sp
                        )
                        Spacer(Modifier.weight(1f)) // This pushes the icon to the far right
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_forward), // Replace with your icon
                            contentDescription = "Change Password", tint = Color(0xFF093030)
                        )
                    }
                }
                // Horizontal separator
                Spacer(modifier = Modifier.height(8.dp)) // Add some space above the separator
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Color(0xFFFFF4EC).copy(alpha = 0.5f)) // Light orange color with alpha
                )
                Spacer(modifier = Modifier.height(16.dp)) // Add some space below the separator

            }
        }
        BottomNavBar(navController = navController, selectedRoute = "profile")
    }
}

@Preview(showBackground = true)
@Composable
fun SecurityPreview() {
    val navController = rememberNavController()// Create a NavController for preview
    Security(navController)
}