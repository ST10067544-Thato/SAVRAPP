package com.example.savr.ui.logic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputField(
    label: String,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    isCompact: Boolean = false, // Add a flag for compact background
) {
    Column {
        Text(
            label,
            color = Color(0xFF093030),
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 29.dp)
                .clip(shape = RoundedCornerShape(18.dp))
                .fillMaxWidth()
                // Apply compact background if isCompact is true
                .background(
                    color = Color(0xFFFFF4EC), shape = RoundedCornerShape(18.dp)
                )
                // Conditional padding based on isCompact
                .padding(
                    vertical = if (isCompact) 10.dp else 15.dp,
                    horizontal = if (isCompact) 18.dp else 35.dp
                )
        ) {
            BasicTextField(value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(color = Color(0xFF093030), fontSize = 16.sp),
                modifier = Modifier.weight(1f), // Fill available space
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(placeholder, color = Color.Gray) // Gray placeholder
                    }
                    innerTextField()
                })
            trailingIcon?.invoke() // Invoke trailing icon if provided
        }
    }
}