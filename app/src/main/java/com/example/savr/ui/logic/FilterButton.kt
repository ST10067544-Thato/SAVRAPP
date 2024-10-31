package com.example.savr.ui.logic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Composable function for a filter button
@Composable
fun FilterButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Color(0xFFFF8D3C) else Color(0xFFFFF4EC)
    val textColor = if (isSelected) Color(0xFFFFFFFF) else Color(0xFF052224)

    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(0.dp, Color.Transparent),
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor), // Set background color
        contentPadding = PaddingValues(),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .width(95.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Text(
                text = text,
                color = textColor, // Set text color
                fontSize = 15.sp,
            )
        }
    }
}

// Enum class for filter types
enum class FilterType {
    DAILY, WEEKLY, MONTHLY, SPENDS, CATEGORIES
}