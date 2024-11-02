package com.example.savr.ui.screens.launchscreens

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.savr.R
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun LaunchScreen(navController: NavController) {
    // Define animation states for multiple stages
    var scale by remember { mutableStateOf(0.5f) }
    var alpha by remember { mutableStateOf(0f) }
    var rotation by remember { mutableStateOf(0f) }
    var translationY by remember { mutableStateOf(0f) }

    // Define animations
    val animatedScale by animateFloatAsState(
        targetValue = scale, animationSpec = tween(durationMillis = 2000, easing = EaseOutExpo)
    )

    val animatedAlpha by animateFloatAsState(
        targetValue = alpha, animationSpec = tween(durationMillis = 2000, easing = EaseInOutCubic)
    )

    val animatedRotation by animateFloatAsState(
        targetValue = rotation,
        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
    )

    val animatedTranslationY by animateFloatAsState(
        targetValue = translationY,
        animationSpec = tween(durationMillis = 1000, easing = FastOutLinearInEasing)
    )

    // Sequential animation stages
    LaunchedEffect(Unit) {
        // Stage 1: Zoom and Fade-in (0 to 2 seconds)
        delay(500) // Initial delay for anticipation
        scale = 1.1f       // Zoom slightly larger
        alpha = 1f         // Full opacity

        // Stage 2: Gentle Rotation and Pulse (2 to 4 seconds)
        delay(2000)
        rotation = 10f     // Small rotation
        scale = 1.2f       // Slight pulse

        // Stage 3: Slide-Up and Fade-Out (4 to 5 seconds)
        delay(2000)
        translationY = -1000f // Slide up far enough to exit entire screen
        alpha = 0f           // Fade-out effect
    }

    LaunchedEffect(key1 = Unit) {
        delay(5000) // Delay for 5 seconds
        navController.navigate("launch_screen_2") {
            popUpTo("launch_screen") { inclusive = true } // Remove LaunchScreen from back stack
        }
    }

    // Layout with background gradient, set clipToBounds = false to prevent clipping
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFF8D3C), Color(0xFFFFB74D))
                )
            ), contentAlignment = Alignment.Center
    ) {
        // Logo with sequential animations
        Image(painter = painterResource(id = R.drawable.savr_logo_white),
            contentDescription = "Enhanced Animated Logo",
            modifier = Modifier
                .size(331.dp, 99.dp) // Set to your logo’s exact dimensions
                .scale(animatedScale)
                .alpha(animatedAlpha)
                .rotate(animatedRotation)
                .offset { IntOffset(0, animatedTranslationY.roundToInt()) })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLaunchScreen() {
    val navController = rememberNavController()// Create a NavController for preview
    LaunchScreen(navController)
}
