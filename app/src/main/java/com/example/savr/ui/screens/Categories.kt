package com.example.savr.ui.screens

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
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
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.CustomNotificationBar

@Composable
fun Categories() {
    // Sample list of categories with icons
    val categories = listOf(
        Pair("Food", R.drawable.food),
        Pair("Transport", R.drawable.transport),
        Pair("Medicine", R.drawable.medicine),
        Pair("Groceries", R.drawable.groceries),
        Pair("Rent", R.drawable.rent),
        Pair("Gifts", R.drawable.gifts),
        Pair("Savings", R.drawable.savings),
        Pair("Entertainment", R.drawable.entertainment),
        Pair("More", R.drawable.more)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF8D3C)) // Base orange background
    ) {
        // Main content area with the "Categories" text
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 20.dp)
                .padding(horizontal = 36.dp) // Horizontal padding for the content
        ) {
            // Custom Notification Bar
            CustomNotificationBar()

            Text(
                "Categories",
                color = Color(0xFFFFFFFF),
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 40.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }

        // Curved white layered page for categories
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Use weight to allocate space properly
                .background(Color(0xFFFFFFFF), RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .verticalScroll(rememberScrollState())
                .padding(top = 40.dp)
        ) {
            // Create rows for category buttons
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                for (i in categories.indices step 3) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(bottom = 32.dp) // Increased bottom padding for more space between rows
                            .fillMaxWidth()
                    ) {
                        // Create button for each category
                        for (j in 0 until 3) {
                            if (i + j < categories.size) {
                                val (label, iconRes) = categories[i + j]
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 5.dp) // Added horizontal padding for spacing
                                ) {
                                    OutlinedButton(
                                        onClick = { /* Handle category selection */ },
                                        border = BorderStroke(0.dp, Color.Transparent),
                                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                                        contentPadding = PaddingValues(),
                                        modifier = Modifier
                                            .clip(shape = RoundedCornerShape(25.dp))
                                            .width(120.dp) // Increased width
                                            .height(120.dp) // Increased height
                                            .background(
                                                color = Color(0xFF6CB5FD),
                                                shape = RoundedCornerShape(25.dp)
                                            )
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center // Center the icon within the Box
                                        ) {
                                            // Display category icon using painterResource
                                            Image(
                                                painter = painterResource(id = iconRes),
                                                contentDescription = label,
                                                modifier = Modifier
                                                    .padding(top = 16.dp)
                                                    .size(60.dp)
                                            )
                                        }
                                    }
                                    // Display category label below the button
                                    Text(
                                        text = label,
                                        color = Color(0xFF093030),
                                        fontSize = 15.sp,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        // Bottom navigation bar
        BottomNavBar()
    }
}


@Preview(showBackground = true)
@Composable
fun CategoriesPreview() {
    Categories()
}


