package com.example.savr.ui.screens.profile.usersettings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.ScreenTopSection

@Composable
fun NotificationSettings(navController: NavController) {
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
            ScreenTopSection(
                navController = navController,
                title = "Notification Settings",
                onBack = { navController.popBackStack() }) // Add this line

        }

        // State for the switches
        var isGeneralNotificationsEnabled by remember { mutableStateOf(false) }
        var isSoundEnabled by remember { mutableStateOf(false) }
        var isVibrateEnabled by remember { mutableStateOf(false) }
        var isTransactionUpdateEnabled by remember { mutableStateOf(false) }
        var isGoalReminderEnabled by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Takes remaining space
                .background(Color(0xFFFFFFFF), RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .verticalScroll(rememberScrollState()) // Enables scrolling
                .padding(top = 50.dp) // Padding to separate from the orange section
        ) { // Expanded Box for LazyColumn and Button
            // Curved white layered page for categories
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White, RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                    )
                    .padding(horizontal = 36.dp) // Add horizontal padding
            ) {

                // General Notifications switch
                SwitchItem(text = "General Notifications",
                    isChecked = isGeneralNotificationsEnabled,
                    onCheckedChange = { isGeneralNotificationsEnabled = it })

                // Sound switch
                SwitchItem(text = "Sound",
                    isChecked = isSoundEnabled,
                    onCheckedChange = { isSoundEnabled = it })

                // Vibrate switch
                SwitchItem(text = "Vibrate",
                    isChecked = isVibrateEnabled,
                    onCheckedChange = { isVibrateEnabled = it })

                // Transaction Update switch
                SwitchItem(text = "Transaction Update",
                    isChecked = isTransactionUpdateEnabled,
                    onCheckedChange = { isTransactionUpdateEnabled = it })

                // Goal Reminder switch
                SwitchItem(text = "Goal Reminder",
                    isChecked = isGoalReminderEnabled,
                    onCheckedChange = { isGoalReminderEnabled = it })
            }


        }
        BottomNavBar(navController = navController, selectedRoute = "profile")
    }
}

// Reusable composable for a switch item
@Composable
fun SwitchItem(text: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    OutlinedButton(
        onClick = { /* Handle action for this item */ },
        border = BorderStroke(0.dp, Color.Transparent),
        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
        contentPadding = PaddingValues(),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                color = Color(0xFF093030),
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(Modifier.weight(1f))
            Switch(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFFFF8D3C),
                    checkedTrackColor = Color(0xFFFF8D3C).copy(alpha = 0.5f),
                ),
                modifier = Modifier.scale(0.8f) // Scale down the switch
            )
        }
    }
    // Horizontal separator - now inside the OutlinedButton
    Spacer(modifier = Modifier.height(8.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color(0xFFFFF4EC).copy(alpha = 0.5f))
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Preview(showBackground = true)
@Composable
fun NotificationSettingsPreview() {
    val navController = rememberNavController()// Create a NavController for preview
    NotificationSettings(navController)
}