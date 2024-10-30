package com.example.savr.ui.screens

import DOBDatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savr.ui.logic.CustomNotificationBar

@Composable
fun SignUp() {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF8D3C)) // Base orange background
    ) {
        // Main content area with the "Create Account" text
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 20.dp)
                .padding(horizontal = 36.dp) // Horizontal padding for the content
        ) {
            // Use the custom notification bar
            CustomNotificationBar()

            // Center the "Create Account" text
            Text(
                "Create Account",
                color = Color(0xFFFFFFFF),
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 47.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally) // Centering
            )
        }

        // Scrollable white layered page
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Takes remaining space
                .background(Color(0xFFFFFFFF), RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .verticalScroll(rememberScrollState()) // Enables scrolling
                .padding(top = 40.dp) // Padding to separate from the orange section
        ) {
            Column(
                horizontalAlignment = Alignment.Start, // Align elements to the start
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp) // Horizontal padding for inner elements
            ) {
                // Full Name field
                Text(
                    "Full Name",
                    color = Color(0xFF093030),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                )
                BasicTextField(value = fullName,
                    onValueChange = { fullName = it }, // Update fullName state
                    textStyle = TextStyle(color = Color(0xFF093030), fontSize = 16.sp),
                    modifier = Modifier
                        .padding(bottom = 29.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFF4EC), shape = RoundedCornerShape(18.dp))
                        .padding(vertical = 15.dp, horizontal = 35.dp),
                    decorationBox = { innerTextField ->
                        if (fullName.isEmpty()) {
                            Text("Enter your full name", color = Color.Gray) // Placeholder text
                        }
                        innerTextField()
                    })

                // Email field
                Text(
                    "Email",
                    color = Color(0xFF093030),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                )
                BasicTextField(value = email,
                    onValueChange = { email = it }, // Update email state
                    textStyle = TextStyle(color = Color(0xFF093030), fontSize = 16.sp),
                    modifier = Modifier
                        .padding(bottom = 29.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFF4EC), shape = RoundedCornerShape(18.dp))
                        .padding(vertical = 15.dp, horizontal = 35.dp),
                    decorationBox = { innerTextField ->
                        if (email.isEmpty()) {
                            Text("Enter your email", color = Color.Gray) // Placeholder text
                        }
                        innerTextField()
                    })

                // Mobile Number field
                Text(
                    "Mobile Number",
                    color = Color(0xFF093030),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                )
                BasicTextField(value = mobileNumber,
                    onValueChange = { mobileNumber = it }, // Update mobileNumber state
                    textStyle = TextStyle(color = Color(0xFF093030), fontSize = 16.sp),
                    modifier = Modifier
                        .padding(bottom = 29.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFF4EC), shape = RoundedCornerShape(18.dp))
                        .padding(vertical = 15.dp, horizontal = 35.dp),
                    decorationBox = { innerTextField ->
                        if (mobileNumber.isEmpty()) {
                            Text("Enter your mobile number", color = Color.Gray) // Placeholder text
                        }
                        innerTextField()
                    })

                // Date of Birth field
                Text(
                    "Date of Birth",
                    color = Color(0xFF093030),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                )
                // DatePicker for date of birth
                DOBDatePicker { selectedDate ->
                    dateOfBirth = selectedDate // Update state with selected date
                }

                // Password field
                Text(
                    "Password",
                    color = Color(0xFF093030),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                )
                BasicTextField(value = password,
                    onValueChange = { password = it }, // Update password state
                    textStyle = TextStyle(color = Color(0xFF093030), fontSize = 16.sp),
                    modifier = Modifier
                        .padding(bottom = 29.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFF4EC), shape = RoundedCornerShape(18.dp))
                        .padding(vertical = 15.dp, horizontal = 35.dp),
                    decorationBox = { innerTextField ->
                        if (password.isEmpty()) {
                            Text("Enter your password", color = Color.Gray) // Placeholder text
                        }
                        innerTextField()
                    })

                // Confirm Password field
                Text(
                    "Confirm Password",
                    color = Color(0xFF093030),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                )
                BasicTextField(value = confirmPassword,
                    onValueChange = { confirmPassword = it }, // Update confirmPassword state
                    textStyle = TextStyle(color = Color(0xFF093030), fontSize = 16.sp),
                    modifier = Modifier
                        .padding(bottom = 29.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFF4EC), shape = RoundedCornerShape(18.dp))
                        .padding(vertical = 15.dp, horizontal = 35.dp),
                    decorationBox = { innerTextField ->
                        if (confirmPassword.isEmpty()) {
                            Text("Confirm your password", color = Color.Gray) // Placeholder text
                        }
                        innerTextField()
                    })

                // Sign Up button
                Button(
                    onClick = { /* Handle Sign Up logic */ },
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(18.dp)),
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFFFF8D3C), // Button background
                        contentColor = Color.White // Button text color
                    )
                ) {
                    Text(text = "Sign Up", fontSize = 16.sp)
                }

                // Centered "Already have an account?" text as a TextButton
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // Centering the content
                ) {
                    TextButton(
                        onClick = { /* TODO: Navigate to Login screen */ },
                        modifier = Modifier.padding(bottom = 20.dp)
                    ) {
                        Text(
                            "Already have an account? Log in",
                            fontSize = 14.sp,
                            color = Color(0xFF093030), // Text color
                            style = TextStyle(textDecoration = TextDecoration.Underline)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignUp()
}
