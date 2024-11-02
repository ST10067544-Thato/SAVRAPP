package com.example.savr.ui.screens.signup

import DOBDatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.savr.ui.logic.CustomNotificationBar

@Composable
fun SignUp(navController: NavController) {
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
        // Header section with "Create Account" text and CustomNotificationBar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 20.dp)
                .padding(horizontal = 36.dp)
        ) {
            CustomNotificationBar()

            Text(
                "Create Account",
                color = Color(0xFFFFFFFF),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 47.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }

        // Scrollable white layered page
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFFFFFFF), RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .padding(top = 40.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp)
            ) {
                item {
                    InputField(label = "Full Name",
                        value = fullName,
                        placeholder = "Enter your full name",
                        onValueChange = { fullName = it })
                }
                item {
                    InputField(label = "Email",
                        value = email,
                        placeholder = "Enter your email",
                        onValueChange = { email = it })
                }
                item {
                    InputField(label = "Mobile Number",
                        value = mobileNumber,
                        placeholder = "Enter your mobile number",
                        onValueChange = { mobileNumber = it })
                }
                item {
                    Text(
                        "Date of Birth",
                        color = Color(0xFF093030),
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                    )
                    DOBDatePicker { selectedDate ->
                        dateOfBirth = selectedDate
                    }
                }
                item {
                    InputField(label = "Password",
                        value = password,
                        placeholder = "Enter your password",
                        onValueChange = { password = it })
                }
                item {
                    InputField(label = "Confirm Password",
                        value = confirmPassword,
                        placeholder = "Confirm your password",
                        onValueChange = { confirmPassword = it })
                }
                item {
                    Button(
                        onClick = { /* Handle Sign Up logic */ },
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .fillMaxWidth()
                            .height(56.dp)
                            .clip(RoundedCornerShape(18.dp)),
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFFFF8D3C), contentColor = Color.White
                        )
                    ) {
                        Text(text = "Sign Up", fontSize = 16.sp)
                    }
                }
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        TextButton(
                            onClick = { navController.navigate("login")  },
                            modifier = Modifier.padding(bottom = 20.dp)
                        ) {
                            Text(
                                "Already have an account? Log in",
                                fontSize = 14.sp,
                                color = Color(0xFF093030),
                                style = TextStyle(textDecoration = TextDecoration.Underline)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InputField(
    label: String,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    Text(
        label,
        color = Color(0xFF093030),
        fontSize = 15.sp,
        modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
    )
    BasicTextField(value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(color = Color(0xFF093030), fontSize = 16.sp),
        modifier = Modifier
            .padding(bottom = 29.dp)
            .clip(RoundedCornerShape(18.dp))
            .fillMaxWidth()
            .background(color = Color(0xFFFFF4EC), shape = RoundedCornerShape(18.dp))
            .padding(vertical = 15.dp, horizontal = 35.dp),
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(placeholder, color = Color.Gray)
            }
            innerTextField()
        })
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    val navController = rememberNavController()// Create a NavController for preview
    SignUp(navController)
}
