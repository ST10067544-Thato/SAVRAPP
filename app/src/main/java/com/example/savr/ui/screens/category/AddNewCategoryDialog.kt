package com.example.savr.ui.screens.category

import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.text.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.focus.onFocusChanged

@Composable
fun AddNewCategory(onDismiss: () -> Unit) {
    var categoryName by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) } // State for focus

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0x801E1E1E))
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
                    "New Category",
                    color = Color(0xFF0E3E3E),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 14.dp, start = 95.dp)
                )
                //placeholder
                BasicTextField(value = categoryName, // Use categoryName state
                    onValueChange = { categoryName = it },
                    textStyle = TextStyle(color = Color(0xFF0E3E3E), fontSize = 14.sp),
                    modifier = Modifier
                        .padding(bottom = 18.dp, start = 32.dp, end = 32.dp)
                        .clip(shape = RoundedCornerShape(18.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFF4EC), shape = RoundedCornerShape(18.dp))
                        .padding(vertical = 14.dp, horizontal = 18.dp)
                        .onFocusChanged { isFocused = it.isFocused }, // Update focus state
                    decorationBox = { innerTextField ->
                        if (categoryName.isEmpty() && !isFocused) { // Show placeholder if empty and not focused
                            Text(
                                "Choose a name for your category...",
                                color = Color(0xFFA5A5A5),
                                fontSize = 14.sp
                            )
                        }
                        innerTextField()
                    })
                //save button to create a new category
                OutlinedButton(
                    onClick = { /* Handle saving the new category here */ },
                    border = BorderStroke(0.dp, Color.Transparent),
                    colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .padding(bottom = 11.dp, start = 57.dp, end = 57.dp)
                        .clip(shape = RoundedCornerShape(30.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFFF8D3C), shape = RoundedCornerShape(30.dp))
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 17.dp)
                    ) {
                        Text("Save", color = Color(0xFFFFFFFF), fontSize = 15.sp)
                    }
                }
                //cancel button
                OutlinedButton(
                    onClick = onDismiss, // Call onDismiss to close the dialog
                    border = BorderStroke(0.dp, Color.Transparent),
                    colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .padding(horizontal = 57.dp)
                        .clip(shape = RoundedCornerShape(30.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFE7E7), shape = RoundedCornerShape(30.dp))
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
