package com.example.savr.ui.logic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilteredResultRow(selectedDate: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text("Groceries", color = Color.Black, fontSize = 16.sp)
            Text("24 April 17:00", color = Color.Gray, fontSize = 12.sp)
        }
        Text("-R100.00", color = Color.Red, fontSize = 16.sp)
    }
}

//Filtered results with nothing passed in it for home screen
@Composable
fun FilteredHomeResultRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text("Groceries", color = Color.Black, fontSize = 16.sp)
            Text("24 April 17:00", color = Color.Gray, fontSize = 12.sp)
        }
        Text("-R100.00", color = Color.Red, fontSize = 16.sp)
    }
}