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
import com.example.savr.R

@Composable
fun BottomNavBar() { // New parameter for the selected icon
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
                onClick = { /* Handle click for icon 1 */ },
                modifier = Modifier.padding(end = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home), // Load the home icon
                    contentDescription = "Home",
                    modifier = Modifier.size(30.dp) // Use size for uniformity
                )
            }

            IconButton(
                onClick = { /* Handle click for icon 2 */ },
                modifier = Modifier.padding(end = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.analysis), // Load the analysis icon
                    contentDescription = "Analysis", modifier = Modifier.size(30.dp)
                )
            }

            IconButton(
                onClick = { /* Handle click for icon 3 */ },
                modifier = Modifier.padding(end = 25.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.transactions), // Load the transactions icon
                    contentDescription = "Transactions", modifier = Modifier.size(30.dp)
                )
            }

            IconButton(
                onClick = { /* Handle click for icon 4 */ },
                modifier = Modifier.padding(end = 28.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.selected_category), // Use the passed icon for category
                    contentDescription = "Category", modifier = Modifier.size(40.dp)
                )
            }

            IconButton(
                onClick = { /* Handle click for icon 5 */ }, modifier = Modifier
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile), // Load the profile icon
                    contentDescription = "Profile", modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}
