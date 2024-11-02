package com.example.savr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.savr.ui.Navigation
import com.example.savr.ui.theme.SAVRTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SAVRTheme {
                Navigation() // Use the Navigation composable
            }
        }
    }
}