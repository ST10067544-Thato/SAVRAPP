package com.example.savr.ui.logic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.savr.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CustomNotificationBar() {
    // State variables for time and battery level
    var currentTime by remember { mutableStateOf("") }
    var batteryLevel by remember { mutableStateOf(100) } // Default to 100%

    // Update time every second
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            kotlinx.coroutines.delay(1000L)
        }
    }

    // Update battery level when changed
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val batteryReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                batteryLevel = level
            }
        }
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        context.registerReceiver(batteryReceiver, filter)

        onDispose {
            context.unregisterReceiver(batteryReceiver)
        }
    }

    // Custom Notification Row
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = currentTime,
            color = Color.White,
            modifier = Modifier.padding(end = 4.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "$batteryLevel%",
            color = Color.White,
            modifier = Modifier.padding(end = 4.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.battery),
            contentDescription = "Battery Icon",
            modifier = Modifier.size(20.dp)
        )
    }
}
