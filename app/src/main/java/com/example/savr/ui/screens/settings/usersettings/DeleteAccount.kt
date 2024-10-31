package com.example.savr.ui.screens.settings.usersettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savr.ui.logic.CustomNotificationBar
import com.example.savr.ui.logic.InputField

@Composable
fun DeleteAccount() {
    //input fields for Current Password. Add padding
    var currentpassword by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF4A3F)) // Base red background
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
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween, // Spread items out
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Delete Account",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f) // Allow text to take up remaining space
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Takes remaining space
                .background(Color(0xFFFFFFFF), RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .verticalScroll(rememberScrollState()) // Enables scrolling
                .padding(top = 50.dp) // Padding to separate from the orange section
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
                // Centered confirmation text
                Text(
                    text = "Are you sure you want to delete your account?",
                    color = Color(0xFF363130),
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(bottom = 25.dp)
                        .fillMaxWidth() // Fill the width
                        .wrapContentSize(Alignment.Center) // Center the text
                )

                // T&C's section
                Column(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(18.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFF4EC), shape = RoundedCornerShape(18.dp))
                        .padding(27.dp)
                ) {
                    Text(
                        text = "This action will permanently delete all of your data, and you will not be able to recover it. Please keep the following in mind before proceeding:\n\nAll your expenses, income and associated transactions will be eliminated.\n\nYou will not be able to access your account or any related information.\n\nThis action cannot be undone.",
                        color = Color(0xFF363130),
                        fontSize = 13.sp,
                        modifier = Modifier.width(264.dp)

                    )
                }

                Box(modifier = Modifier.padding(top = 30.dp)) { // Wrap with Box
                    InputField(label = "Please enter your password to confirm deletion of your account.",
                        value = currentpassword,
                        placeholder = "Enter your password.",
                        onValueChange = { currentpassword = it })
                }

                Button(
                    onClick = { /* Handle logic */ },
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 50.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(18.dp)),
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFFFF4A3F), contentColor = Color.White
                    )
                ) {
                    Text(text = "Yes, Delete My Account", fontSize = 16.sp)
                }

                Button(
                    onClick = { /* Handle logic */ },
                    modifier = Modifier
                        .padding(vertical = 15.dp, horizontal = 80.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(18.dp)),
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFFFFE7E7), contentColor = Color.Black
                    )
                ) {
                    Text(text = "Cancel", fontSize = 16.sp)
                }
            }
        }
        // BottomNavBar()
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteAccountPreview() {
    DeleteAccount()
}