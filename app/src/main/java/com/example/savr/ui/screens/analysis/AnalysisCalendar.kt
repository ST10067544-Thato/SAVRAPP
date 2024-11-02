package com.example.savr.ui.screens.analysis

import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.CategoryPieChart
import com.example.savr.ui.logic.CustomNotificationBar
import com.example.savr.ui.logic.FilterButton
import com.example.savr.ui.logic.FilterType
import com.example.savr.ui.logic.ScreenTopSection

@Composable
fun AnalysisCalendar(navController: NavController) {
    val currentDate = Calendar.getInstance()
    var selectedFilter by remember { mutableStateOf(FilterType.SPENDS) }
    var selectedDate by remember { mutableStateOf(currentDate.get(Calendar.DAY_OF_MONTH).toString()) }

    // State for month and year selection, initialized with current date
    var selectedMonth by remember { mutableStateOf(currentDate.get(Calendar.MONTH) + 1) }
    var selectedYear by remember { mutableStateOf(currentDate.get(Calendar.YEAR)) }

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
            ScreenTopSection(navController = navController, title = "Calendar Overview", onBack = { navController.popBackStack() }) // Add this line
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
                var expandedMonth by remember { mutableStateOf(false) }
                var expandedYear by remember { mutableStateOf(false) }

                // Month and Year navigation with dropdown
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween // Align items to edges
                ) {
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
                        DropdownMenu(expanded = expandedMonth,
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
                    DropdownMenu(expanded = expandedYear,
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
                    horizontalArrangement = Arrangement.SpaceAround, // Use SpaceAround for even distribution
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                        .fillMaxWidth()
                ) {
                    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                    daysOfWeek.forEach { day ->
                        Box( // Wrap each day header in a Box
                            modifier = Modifier
                                .weight(1f) // Distribute weight evenly
                                .wrapContentSize(Alignment.Center) // Center content in the Box
                        ) {
                            Text(day, color = Color(0xFF3299FF), fontSize = 14.sp)
                        }
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
                        horizontalArrangement = Arrangement.SpaceAround, // Use SpaceAround for even distribution
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
                    //FilteredHomeResultRow(navController) // Pass selected date to FilteredResultRow
                } else {
                    CategoryPieChart(selectedDate) // Pass selectedDate to PieChart
                }
            }
        }
        // Bottom navigation
        BottomNavBar(navController = navController, selectedRoute = "analysis")
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

@Preview(showBackground = true)
@Composable
fun AnalysisCalendarPreview() {
    val navController = rememberNavController()// Create a NavController for preview
    AnalysisCalendar(navController)
}


