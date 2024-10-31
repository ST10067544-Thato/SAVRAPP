package com.example.savr.ui.screens.transactions

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.savr.R
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.CustomNotificationBar
import com.example.savr.ui.logic.FilteredHomeResultRow

@Composable
fun Transactions() {
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
                    text = "Transactions",
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

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 17.dp, start = 36.dp, end = 36.dp)
                .clip(shape = RoundedCornerShape(14.dp))
                .fillMaxWidth()
                .background(
                    color = Color(0xFFFFFFFF), shape = RoundedCornerShape(14.dp)
                )
                .padding(vertical = 16.dp)
        ) {
            Text(
                "Total Balance",
                color = Color(0xFF093030),
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 11.dp)
            )
            Text(
                "R7,783.00",
                color = Color(0xFF093030),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 23.dp, start = 36.dp, end = 36.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.padding(end = 10.dp)) {
                IncomeExpenseCard(
                    title = "Income",
                    amount = "R4,120.00",
                    icon = R.drawable.transaction_income,
                    amountColor = Color(0xFF093030)
                )
            }

            Box(modifier = Modifier.padding(end = 8.dp)) {
                IncomeExpenseCard(
                    title = "Expense",
                    amount = "R1,187.40",
                    icon = R.drawable.transaction_expenses,
                    amountColor = Color(0xFF0068FF)
                )
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
                    TransactionSection(month)
                }
            }

            // Floating button with outer shadow, positioned just above the bottom navigation bar
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp) // Adjusted padding for lower positioning
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
                        "+ Add Expense", color = Color.White, fontSize = 15.sp
                    )
                }
            }
        }

        BottomNavBar() // Place the Bottom Navigation Bar here to keep it visible
    }
}

@Composable
fun TransactionSection(month: String) {
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

@Composable
fun IncomeExpenseCard(
    title: String,
    amount: String,
    icon: Int,
    amountColor: Color,
    modifier: Modifier = Modifier // Add modifier parameter
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(14.dp))
            .width(171.dp) // Set a fixed width
            .background(Color.White, shape = RoundedCornerShape(14.dp))
            .padding(vertical = 15.dp, horizontal = 8.dp), // Add horizontal padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = title,
            modifier = Modifier
                .size(25.dp) // Consistent icon size
                .padding(bottom = 5.dp)
        )
        Text(
            text = title,
            color = Color(0xFF093030),
            fontSize = 15.sp,
            textAlign = TextAlign.Center, // Center text
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = amount,
            color = amountColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center // Center text
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionsPreview() {
    Transactions()
}