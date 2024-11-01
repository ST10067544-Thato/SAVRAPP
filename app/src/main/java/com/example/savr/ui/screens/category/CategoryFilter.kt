package com.example.savr.ui.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.savr.R
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.CustomNotificationBar
import com.example.savr.ui.logic.FilteredHomeResultRow

@Composable
fun CategoryFilter() {
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
                    text = "Groceries",
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

        Box(modifier = Modifier.weight(1f)) { // Expanded Box for LazyColumn and Button
            // Curved white layered page for categories
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                    .padding(top = 26.dp)
            ) {
                items(listOf("April", "March")) { month ->
                    FilteredTransactions(month)
                }
            }
        }
        BottomNavBar() // Place the Bottom Navigation Bar here to keep it visible
    }
}

@Composable
fun FilteredTransactions(month: String) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp) // Add bottom padding to create space between sections
    ) {
        Text(
            text = month,
            color = Color(0xFF093030),
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 36.dp, bottom = 8.dp)
        )
        // Display transactions for the given month vertically
        Column {
            FilteredHomeResultRow()
            FilteredHomeResultRow()
            FilteredHomeResultRow()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryFilterPreview() {
    CategoryFilter()
}