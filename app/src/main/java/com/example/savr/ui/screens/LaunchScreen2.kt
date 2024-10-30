package com.example.savr.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savr.R

@Composable
fun LaunchScreen2() {
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen
            .background(Color(0xFFFFFFFF))
            .clip(RoundedCornerShape(40.dp)) // Rounded corners for the background
            .padding(horizontal = 16.dp) // Optional horizontal padding for content
    ) {
        // Spacer to push content to the center vertically
        Spacer(modifier = Modifier.weight(1f)) // Pushes content down

        // Centered Row for the logo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp), // Adjust padding as needed
            horizontalArrangement = Arrangement.Center // Center logo
        ) {
            Image(
                painter = painterResource(id = R.drawable.savr_logo), // Use your local image resource
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp) // Set the size for the logo
            )
        }

        // Button for Log In
        Button(
            onClick = { /* Handle Log In click */ },
            modifier = Modifier
                .fillMaxWidth(0.6f) // Make button 60% of the screen width
                .align(Alignment.CenterHorizontally) // Center the button
                .padding(bottom = 8.dp) // Smaller bottom padding
                .clip(RoundedCornerShape(30.dp)), // Rounded corners for the button
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8D3C)) // Button color
        ) {
            Text(
                text = "Log In", color = Color(0xFFFFFFFF), fontSize = 16.sp, // Smaller font size
                modifier = Modifier.padding(vertical = 8.dp) // Padding for button text
            )
        }

        // Button for Sign Up
        Button(
            onClick = { /* Handle Sign Up click */ },
            modifier = Modifier
                .fillMaxWidth(0.6f) // Make button 60% of the screen width
                .align(Alignment.CenterHorizontally) // Center the button
                .clip(RoundedCornerShape(30.dp)), // Rounded corners for the button
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFE7E7)) // Button color
        ) {
            Text(
                text = "Sign Up", color = Color(0xFF0E3E3E), fontSize = 16.sp, // Smaller font size
                modifier = Modifier.padding(vertical = 8.dp) // Padding for button text
            )
        }

        // Spacer to push content to the center vertically
        Spacer(modifier = Modifier.weight(1f)) // Pushes content up
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLaunchScreen2() {
    LaunchScreen2()
}
