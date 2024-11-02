package com.example.savr.ui.screens.profile.editprofile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.shadow
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
import com.example.savr.ui.logic.InputField
import com.example.savr.ui.logic.ScreenTopSection


@Composable
fun EditProfile(navController: NavController) {
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
            ScreenTopSection(navController = navController, title = "Edit Profile", onBack = { navController.popBackStack() }) // Add this line
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
                // Profile Picture with Camera Icon - Centered on the white section
                Box(
                    modifier = Modifier
                        .fillMaxWidth() // Fill the width of the white section
                        .wrapContentSize(Alignment.Center) // Wrap content and center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_placeholder),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .align(Alignment.Center) // Center the image within the Box
                            .padding(bottom = 8.dp)
                    )
                    IconButton(
                        onClick = { /* Handle profile picture change */ },
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFF8D3C))
                            .align(Alignment.BottomEnd) // Position at bottom end
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = "Change Profile Picture",
                            tint = Color.White
                        )
                    }
                }

                Text(
                    "John Smith",
                    color = Color(0xFF0E3E3E),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally) // Center horizontally
                        .padding(top = 8.dp, bottom = 40.dp)
                )

                //input fields for Full Name, email address and cellphone number must be automatically filled based on user logged in
                var fullName by remember { mutableStateOf("John Smith") }
                var email by remember { mutableStateOf("example@example.com") }
                var phone by remember { mutableStateOf("+44 555 5555 55") }

                InputField(label = "Full Name",
                    value = fullName,
                    placeholder = "Enter your full name",
                    onValueChange = { fullName = it })

                InputField(label = "Email Address",
                    value = email,
                    placeholder = "Enter your email address",
                    onValueChange = { email = it })

                InputField(label = "Phone Number",
                    value = phone,
                    placeholder = "Enter your phone number",
                    onValueChange = { phone = it })
            }
            // Floating button with outer shadow, positioned just above the bottom navigation bar
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp) // Adjusted padding for lower positioning
                    .shadow(
                        elevation = 2.dp, // Shadow for outer effect
                        shape = RoundedCornerShape(30.dp),
                        clip = false // Ensures shadow is outside the button shape
                    )
            ) {
                OutlinedButton(
                    onClick = { /* Handle button click */ },
                    border = BorderStroke(0.dp, Color.Transparent),
                    colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .width(169.dp)
                        .height(36.dp)
                        .background(
                            Color(0xFFFF8D3C), shape = RoundedCornerShape(30.dp)
                        )
                ) {
                    Text(
                        "Update Profile", color = Color.White, fontSize = 15.sp
                    )
                }
            }
        }
        BottomNavBar(navController = navController, selectedRoute = "profile")
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
    val navController = rememberNavController()// Create a NavController for preview
    EditProfile(navController)
}