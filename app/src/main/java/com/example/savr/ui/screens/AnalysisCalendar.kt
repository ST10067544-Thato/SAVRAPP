package com.example.savr.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.CustomNotificationBar
import com.example.savr.ui.logic.FilterButton
import com.example.savr.ui.logic.FilterType
import com.example.savr.ui.logic.FilteredResultRow

@Composable
fun AnalysisCalendar() {
    var selectedFilter by remember { mutableStateOf(FilterType.SPENDS) }
    var selectedDate by remember { mutableStateOf("23") } // Default selected date

    // State for month and year selection
    var selectedMonth by remember { mutableStateOf(4) } // Default to April (1-based index)
    var selectedYear by remember { mutableStateOf(2024) } // Default year

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF8D3C)) // Base orange background
    ) {
        // Notification Bar and Title
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 20.dp)
                .padding(horizontal = 36.dp)
        ) {
            CustomNotificationBar()
            Text(
                text = "Calendar",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 40.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }

        // Curved white layered page for Calendar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    Color.White, shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                )
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .padding(horizontal = 36.dp)
                    .fillMaxWidth()
            ) {
                var expandedYear by remember { mutableStateOf(false) }
                // Month and Year navigation with dropdown
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween // Align items to edges
                ) {
                    var expandedMonth by remember { mutableStateOf(false) }

                    Row { // Wrap month selection in a Row
                        TextButton(onClick = { expandedMonth = !expandedMonth }) {
                            Row {
                                Text(
                                    getMonthName(selectedMonth),
                                    color = Color(0xFFFF8D3C),
                                    fontSize = 15.sp
                                )
                                Text("▼", color = Color(0xFFFF8D3C), fontSize = 12.sp)
                            }
                        }

                        /// Month Dropdown
                        DropdownMenu(
                            expanded = expandedMonth,
                            onDismissRequest = { expandedMonth = false }) {
                            (1..12).forEach { month ->
                                DropdownMenuItem(onClick = {
                                    selectedMonth = month
                                    expandedMonth = false
                                }, text = { Text(getMonthName(month)) })
                            }
                        }
                    } // End of month selection Row

                    TextButton(onClick = { expandedYear = !expandedYear }) { // Year selection
                        Text(selectedYear.toString(), color = Color(0xFFFF8D3C), fontSize = 15.sp)
                    }

                    // Year Dropdown (last 5 years)
                    DropdownMenu(
                        expanded = expandedYear,
                        onDismissRequest = { expandedYear = false }) {
                        (2019..2024).forEach { year ->
                            DropdownMenuItem(onClick = {
                                selectedYear = year
                                expandedYear = false
                            }, text = { Text(year.toString()) })
                        }
                    }
                }
                
                // Day headers (Mon-Sun)
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                        .fillMaxWidth()
                ) {
                    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                    daysOfWeek.forEach { day ->
                        Text(day, color = Color(0xFF3299FF), fontSize = 14.sp)
                    }
                }

                // Dates for a typical month with fixed box width for alignment
                val daysInMonth = listOf(
                    listOf("", "", "1", "2", "3", "4", "5"),
                    listOf("6", "7", "8", "9", "10", "11", "12"),
                    listOf("13", "14", "15", "16", "17", "18", "19"),
                    listOf("20", "21", "22", "23", "24", "25", "26"),
                    listOf("27", "28", "29", "30", "", "", "")
                )
                daysInMonth.forEach { week ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        week.forEach { day ->
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(40.dp) // Fixed size for consistent column alignment
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (day == selectedDate) Color(0xFFFF8D3C) else Color.White)
                            ) {
                                TextButton(
                                    onClick = {
                                        selectedDate = day // Update selected date
                                    }, colors = ButtonDefaults.textButtonColors(
                                        Color.Transparent // No background color for button
                                    )
                                ) {
                                    Text(
                                        text = day,
                                        color = if (day == selectedDate) Color.White else Color(
                                            0xFF052224
                                        ),
                                        fontSize = 12.5.sp
                                    )
                                }
                            }
                        }
                    }
                }

                // Filter buttons for "Spends" and "Categories"
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 15.dp, horizontal = 44.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFFFF4EC), shape = RoundedCornerShape(22.dp)
                        )
                        .padding(vertical = 1.dp) // Add horizontal padding
                        .wrapContentSize(
                            align = Alignment.Center, unbounded = true
                        )
                ) {
                    FilterButton( // Use the provided FilterButton composable
                        text = "Spends",
                        isSelected = selectedFilter == FilterType.SPENDS,
                        onClick = { selectedFilter = FilterType.SPENDS })

                    FilterButton( // Use the provided FilterButton composable
                        text = "Categories",
                        isSelected = selectedFilter == FilterType.CATEGORIES,
                        onClick = { selectedFilter = FilterType.CATEGORIES })
                }

                // Conditional content based on selected filter
                if (selectedFilter == FilterType.SPENDS) {
                    FilteredResultRow(selectedDate) // Pass selected date to FilteredResultRow
                } else {
                    CategoryPieChart(selectedDate) // Pass selectedDate to PieChart
                }
            }
        }
        // Bottom navigation
        BottomNavBar()
    }
}

private fun getMonthName(month: Int): String {
    return when (month) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> "Unknown"
    }
}

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

                drawContext.canvas.nativeCanvas.drawText("$percentage%",
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

@Preview(showBackground = true)
@Composable
fun AnalysisCalendarPreview() {
    AnalysisCalendar()
}


