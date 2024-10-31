package com.example.savr.ui.screens.settings.security

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savr.R
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.CustomNotificationBar
import com.example.savr.ui.logic.InputField

@Composable
fun ChangePassword() {
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
            // Custom Notification Bar
            CustomNotificationBar()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween, // Spread items out
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Handle back navigation */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Change Password",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f) // Allow text to take up remaining space
                )
                IconButton(onClick = { /* Handle notifications */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notifications),
                        contentDescription = "Notifications",
                        tint = Color.White
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Takes remaining space
                .background(Color(0xFFFFFFFF), RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .verticalScroll(rememberScrollState()) // Enables scrolling
                .padding(top = 60.dp) // Padding to separate from the orange section
        ) { // Expanded Box for LazyColumn and Button
            // Curved white layered page for categories
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White, RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                    )
                    .padding(horizontal = 36.dp) // Add horizontal padding
            ){
            //input fields for Current Password, New Password and Confirm New Password.
            var currentpassword by remember { mutableStateOf("") }
            var newpassword by remember { mutableStateOf("") }
            var confirmnewpassword by remember { mutableStateOf("") }

            InputField(label = "Current Password",
                value = currentpassword,
                placeholder = "Enter your current password.",
                onValueChange = { currentpassword = it })

            InputField(label = "New Password",
                value = newpassword,
                placeholder = "Enter your new password.",
                onValueChange = { newpassword = it })

            InputField(label = "Confirm New Password",
                value = confirmnewpassword,
                placeholder = "Re-enter your new password.",
                onValueChange = { confirmnewpassword = it })

                Button(
                    onClick = { /* Handle logic */ },
                    modifier = Modifier
                        .padding(vertical = 20.dp, horizontal = 70.dp)
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(18.dp)),
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFFFF8D3C), contentColor = Color.White
                    )
                ) {
                    Text(text = "Save Changes", fontSize = 16.sp)
                }
        }
        }
        BottomNavBar()
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordPreview() {
    ChangePassword()
}