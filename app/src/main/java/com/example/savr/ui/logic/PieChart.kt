package com.example.savr.ui.logic

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryPieChart(selectedDate: String) {
    // Dummy data for visualization
    val categoryData = mapOf(
        "Grocery" to 8, // 8 spends in Grocery category
        "Car" to 2 // 2 spends in Car category
    )

    val colors = listOf(Color(0xFF3299FF), Color(0xFF6CB5FD)) // Blue color shades

    // Using Column to stack pie chart and legend directly
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // General padding for the whole column
            .wrapContentSize(Alignment.Center), // Center align the content
        horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
    ) {
        // Pie chart canvas
        Canvas(modifier = Modifier
            .size(150.dp) // Ensure the canvas is square
            .graphicsLayer {
                shadowElevation = 8.dp.toPx() // Add shadow
                clip = true // Clip to bounds for shadow
                shape = CircleShape // Make the shadow circular
            }) {
            var startAngle = 0f // Start from 0 for a full circle
            val totalSpends = categoryData.values.sum()

            categoryData.forEach { (category, spends) ->
                val sweepAngle = (spends.toFloat() / totalSpends) * 360f // Full circle
                drawArc(
                    color = colors[categoryData.keys.indexOf(category) % colors.size],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true, // Optionally fill the center if you want a solid look
                )

                // Calculate percentage and display in white
                val percentage = ((spends.toFloat() / totalSpends) * 100).toInt()
                val angle = startAngle + sweepAngle / 2
                val radius = size.minDimension / 2
                val x = center.x + radius * 0.5f * kotlin.math.cos(Math.toRadians(angle.toDouble()))
                    .toFloat()
                val y = center.y + radius * 0.5f * kotlin.math.sin(Math.toRadians(angle.toDouble()))
                    .toFloat()

                drawContext.canvas.nativeCanvas.drawText(
                    "$percentage%",
                    x,
                    y,
                    android.graphics.Paint().apply {
                        this.color = android.graphics.Color.WHITE // Set text color to white
                        textSize = 12.sp.toPx()
                        textAlign = android.graphics.Paint.Align.CENTER
                    })

                startAngle += sweepAngle
            }
        }

        // Legend directly below the pie chart
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp), // Adjust spacing as needed
            horizontalArrangement = Arrangement.Center // Center items in the row
        ) {
            val legendItems = categoryData.entries.toList()
            for (i in legendItems.indices) {
                LegendItem(legendItems[i].key, colors[i % colors.size])
                if (i % 2 == 0 && i + 1 < legendItems.size) { // Add spacer after every 2 items
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}

//function for a single legend item
@Composable
fun LegendItem(category: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(16.dp, 16.dp)
                .background(color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = category, fontSize = 12.sp)
    }
}