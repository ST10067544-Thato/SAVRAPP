package com.example.savr.ui.screens.category

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LogoutDialog(onDismiss: () -> Unit, onLogout: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0x801E1E1E)) // Semi-transparent dark background
            .clickable(onClick = onDismiss)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center) // Center the dialog vertically and horizontally
                .padding(horizontal = 45.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(60.dp))
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(60.dp))
                    .padding(vertical = 76.dp)
            ) {
                Text(
                    "End Session",
                    color = Color(0xFF0E3E3E),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 14.dp)
                        .align(Alignment.CenterHorizontally) // Center the title horizontally
                )

                // Confirmation message
                Text(
                    text = "Are you sure you want to logout?",
                    color = Color(0xFF0E3E3E),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .align(Alignment.CenterHorizontally) // Center the message horizontally
                )

                // Logout button (red with white text)
                OutlinedButton(
                    onClick = onLogout,
                    border = BorderStroke(0.dp, Color.Transparent),
                    colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .padding(bottom = 11.dp, start = 57.dp, end = 57.dp)
                        .clip(shape = RoundedCornerShape(30.dp))
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFFF4A3F), shape = RoundedCornerShape(30.dp)
                        ) // Red background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 17.dp)
                    ) {
                        Text("Logout", color = Color(0xFFFFFFFF), fontSize = 15.sp) // White text
                    }
                }

                // Cancel button (light red)
                OutlinedButton(
                    onClick = onDismiss,
                    border = BorderStroke(0.dp, Color.Transparent),
                    colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .padding(horizontal = 57.dp)
                        .clip(shape = RoundedCornerShape(30.dp))
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFFFE7E7), shape = RoundedCornerShape(30.dp)
                        ) // Light red background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 16.dp)
                    ) {
                        Text("Cancel", color = Color(0xFF0E3E3E), fontSize = 15.sp)
                    }
                }
            }
        }
    }
}