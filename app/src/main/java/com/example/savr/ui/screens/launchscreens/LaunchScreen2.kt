package com.example.savr.ui.screens.launchscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.savr.R

@Composable
fun LaunchScreen2(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .clip(RoundedCornerShape(40.dp))
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.savr_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )
        }

        Button(
            onClick = { navController.navigate("login") }, // Navigate to Login
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
                .clip(RoundedCornerShape(30.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8D3C))
        ) {
            Text(
                text = "Log In",
                color = Color(0xFFFFFFFF),
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Button(
            onClick = { navController.navigate("signup") }, // Navigate to SignUp
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(30.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFE7E7))
        ) {
            Text(
                text = "Sign Up",
                color = Color(0xFF0E3E3E),
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLaunchScreen2() {
    val navController = rememberNavController()// Create a NavController for preview
    LaunchScreen2(navController)
}
