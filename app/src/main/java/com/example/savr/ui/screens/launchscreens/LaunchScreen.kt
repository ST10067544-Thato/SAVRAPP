package com.example.savr.ui.screens.launchscreens

import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.savr.R

@Composable
fun LaunchScreen() {
    // Define the gradient background from dark orange to light orange
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFF8D3C), Color(0xFFFFB74D)), // Dark orange to light orange
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen
            .background(gradientBrush) // Use the gradient as the background
            .clip(RoundedCornerShape(40.dp)) // Rounded corners for the background
            .padding(horizontal = 16.dp) // Optional horizontal padding for content
    ) {
        Spacer(modifier = Modifier.weight(1f)) // Spacer to push content to the center

        // Centered Row for the local GIF
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp), // Adjust padding as needed
            horizontalArrangement = Arrangement.Center // Center the GIF
        ) {
            // Load the local GIF from drawable resources
            Image(
                painter = painterResource(id = R.drawable.loading), // Replace with your GIF file name
                contentDescription = "Loading GIF",
                modifier = Modifier.size(300.dp), // Set the size for the GIF
                contentScale = ContentScale.Fit // Scale the image as needed
            )
        }
        Spacer(modifier = Modifier.weight(1f)) // Spacer to push content up
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLaunchScreen() {
    LaunchScreen()
}
