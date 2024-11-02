package com.example.savr.ui.logic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.savr.R

@Composable
fun FilteredResultRow(selectedDate: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFFFF4EC))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Icon with blue background
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp)) // Adjust corner radius as needed
                    .background(Color(0xFF6CB5FD))
                    .padding(8.dp) // Adjust padding as needed
            ) {
                Image(
                    painter = painterResource(id = R.drawable.groceries),
                    contentDescription = "Transaction Icon",
                    modifier = Modifier.size(24.dp) // Adjust size as needed
                )
            }

            Spacer(modifier = Modifier.width(16.dp)) // Add spacing between icon and text

            Column {
                Text(
                    text = "Groceries",
                    color = Color(0xFF052224),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "24 April 17:00",
                    color = Color(0xFF808080),
                    fontSize = 12.sp
                )
            }
        }
        Text(
            text = "-R100.00",
            color = Color(0xFF0068FF),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

//Filtered results with nothing passed in it for home screen
@Composable
fun FilteredHomeResultRow(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFFFF4EC))
            .padding(16.dp)
            .clickable { navController.navigate("add_expense") }, // Make clickable
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Icon with blue background
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp)) // Adjust corner radius as needed
                    .background(Color(0xFF6CB5FD))
                    .padding(8.dp) // Adjust padding as needed
            ) {
                Image(
                    painter = painterResource(id = R.drawable.groceries),
                    contentDescription = "Transaction Icon",
                    modifier = Modifier.size(24.dp) // Adjust size as needed
                )
            }

            Spacer(modifier = Modifier.width(16.dp)) // Add spacing between icon and text

            Column {
                Text(
                    text = "Groceries",
                    color = Color(0xFF052224),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "24 April 17:00",
                    color = Color(0xFF808080),
                    fontSize = 12.sp
                )
            }
        }
        Text(
            text = "-R100.00",
            color = Color(0xFF0068FF),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}