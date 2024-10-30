package com.example.savr.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.savr.R
import com.example.savr.ui.logic.CustomNotificationBar

@Composable
fun Login() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF8D3C)) // Set the base orange background
    ) {
        // Main content area with the welcome text
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 20.dp)
                .padding(horizontal = 36.dp) // Horizontal padding for the content
        ) {
            // Use the custom notification bar
            CustomNotificationBar()

            // Center the "Welcome" text
            Text("Welcome",
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
                // Username or email field
                Text("Username or email",
                    color = Color(0xFF093030),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp) // Padding to separate from the field
                )
                BasicTextField(
                    value = email,
                    onValueChange = { email = it }, // Update email state
                    textStyle = TextStyle(color = Color(0xFF093030), fontSize = 16.sp),
                    modifier = Modifier
                        .padding(bottom = 29.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFF4EC), shape = RoundedCornerShape(18.dp))
                        .padding(vertical = 15.dp, horizontal = 35.dp),
                    decorationBox = { innerTextField -> // Add a placeholder
                        if (email.isEmpty()) {
                            Text("Enter your email", color = Color.Gray) // Placeholder text
                        }
                        innerTextField() // Draw the text field content
                    }
                )

                // Password field label
                Text("Password",
                    color = Color(0xFF093030),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 91.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFF4EC), shape = RoundedCornerShape(18.dp))
                        .padding(top = 17.dp, bottom = 17.dp, start = 35.dp, end = 15.dp)
                ) {
                    BasicTextField(
                        value = password,
                        onValueChange = { password = it },
                        textStyle = TextStyle(color = Color(0xFF093030), fontSize = 16.sp),
                        modifier = Modifier
                            .weight(1f) // Occupies remaining space
                            .padding(end = 8.dp), // Space between text and icon
                        decorationBox = { innerTextField ->
                            if (password.isEmpty()) {
                                Text("Enter your password", color = Color.Gray, fontSize = 14.sp)
                            }
                            innerTextField()
                        }
                    )

                    IconButton(
                        onClick = { /* TODO: Add logic to toggle password visibility */ },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.show_password),
                            contentDescription = "Show Password Icon"
                        )
                    }
                }
                // Centered Log In button
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // Centering the button
                ) {
                    Button(
                        onClick = { /* TODO: Add log in logic */ },
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFFFF8D3C), // Orange background
                            contentColor = Color.White // White text color
                        ),
                        modifier = Modifier
                            .padding(bottom = 6.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .fillMaxWidth(0.65f) // Full width to ensure proper centering
                    ) {
                        Text("Log In",
                            fontSize = 20.sp,
                        )
                    }
                }

                // Centered "Forgot Password?" as a TextButton
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // Centering the content
                ) {
                    TextButton(
                        onClick = { /* TODO: Add logic for password recovery */ },
                        modifier = Modifier.padding(bottom = 3.dp)
                    ) {
                        Text(
                            "Forgot Password?",
                            fontSize = 14.sp,
                            color = Color(0xFF093030) // Text color
                        )
                    }
                }


                // Centered Sign Up button
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // Centering the button
                ) {
                    Button(
                        onClick = { /* TODO: Add sign up logic */ },
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFFFFE7E7), // Light background
                            contentColor = Color(0xFF0E3E3E) // Dark text color
                        ),
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .fillMaxWidth(0.65f) // Full width to ensure proper centering
                    ) {
                        Text("Sign Up",
                            fontSize = 20.sp,
                        )
                    }
                }

                // Centered fingerprint text as a TextButton
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // Centering the content
                ) {
                    TextButton(
                        onClick = { /* TODO: Add logic to activate biometrics */ },
                        modifier = Modifier.padding(bottom = 7.dp)
                    ) {
                        Text(
                            "Use fingerprint to log in",
                            fontSize = 14.sp,
                            color = Color(0xFF093030), // Text color
                            style = TextStyle(textDecoration = TextDecoration.Underline)
                        )
                    }
                }


                // Centered text for sign-in options
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // Centering the content
                ) {
                    Text("or sign in using",
                        color = Color(0xFF093030),
                        fontSize = 13.sp,
                        modifier = Modifier.padding(bottom = 50.dp)
                    )
                    // Clickable Google icon using a local image
                    IconButton(
                        onClick = { /* TODO: Add logic for Google sign in */ },
                        modifier = Modifier
                            .size(70.dp)
                            .padding(top = 20.dp) // Adjust for vertical spacing
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_2), // Local image for Google icon
                            contentDescription = "Google Sign In Icon"
                        )
                    }
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen()
{
    Login()
}
