package com.example.savr.ui.screens.analysis

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.CategoryPieChart
import com.example.savr.ui.logic.DisplayExpense
import com.example.savr.ui.logic.EmptyState
import com.example.savr.ui.logic.FilterButton
import com.example.savr.ui.logic.FilterType
import com.example.savr.ui.logic.ScreenTopSection
import com.example.savr.ui.viewmodels.HomeViewModel
import java.time.LocalDate

@SuppressLint("NewApi")
@Composable
fun AnalysisCalendar(navController: NavController, viewModel: HomeViewModel) {
    val expenses by viewModel.expenses.collectAsState()
    val categories by viewModel.categories.collectAsState()

    val currentDate = LocalDate.now()
    var selectedFilter by remember { mutableStateOf(FilterType.SPENDS) } // Spends button clicked by default
    var selectedDate by remember { mutableStateOf(currentDate) }

    // State for month and year selection, initialized with current date
    var selectedMonth by remember { mutableStateOf(currentDate.monthValue) }
    var selectedYear by remember { mutableStateOf(currentDate.year) }

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
            ScreenTopSection(navController = navController,
                title = "Calendar Overview",
                onBack = { navController.popBackStack() }) // Add this line
        }

        // Curved white layered page for Calendar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    Color.White, shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                )
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
                val daysInMonth = getDaysInMonth(selectedYear, selectedMonth)
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
                                    .background(
                                        if (day == selectedDate.dayOfMonth.toString()) Color(
                                            0xFFFF8D3C
                                        ) else Color.White
                                    )
                            ) {
                                TextButton(
                                    onClick = {
                                        selectedDate = LocalDate.of(
                                            selectedYear, selectedMonth, day.toIntOrNull() ?: 1
                                        ) // Update selected date
                                    }, colors = ButtonDefaults.textButtonColors(
                                        Color.Transparent // No background color for button
                                    )
                                ) {
                                    Text(
                                        text = day,
                                        color = if (day == selectedDate.dayOfMonth.toString()) Color.White else Color(
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
                    val filteredExpenses = expenses.filter { it.date == selectedDate }
                    if (filteredExpenses.isEmpty()) {
                        EmptyState(message = "No transactions available")
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 70.dp)
                        ) {
                            items(filteredExpenses) { expense ->
                                val category = categories.find { it.id == expense.categoryId }
                                DisplayExpense(navController, expense, category)
                            }
                        }
                    }
                } else {
                    val categoryData =
                        expenses.filter { it.date == selectedDate }.groupBy { it.categoryId }
                            .mapValues { (_, expenses) -> expenses.sumOf { it.amount } }
                    if (categoryData.isEmpty()) {
                        EmptyState(message = "No data available for this date")
                    } else {
                        CategoryPieChart(
                            selectedDate, expenses, categories
                        )// Pass selectedDate to PieChart
                    }
                }
            }
        }
        // Bottom navigation
        BottomNavBar(navController = navController, selectedRoute = "analysis")
    }
}

private fun getDaysInMonth(year: Int, month: Int): List<List<String>> {
    val calendar = Calendar.getInstance().apply {
        set(year, month - 1, 1) // Set to the first day of the month
    }
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1 // Adjust to 0-based index

    val daysList = mutableListOf<List<String>>()
    val currentWeek = mutableListOf<String>()

    // Add empty cells for days before the first day of the month
    for (i in 0 until firstDayOfWeek) {
        currentWeek.add("")
    }

    // Add days of the month
    for (day in 1..daysInMonth) {
        currentWeek.add(day.toString())
        if (currentWeek.size == 7) {
            daysList.add(currentWeek.toList())
            currentWeek.clear()
        }
    }

    // Add empty cells for days after the last day of the month
    if (currentWeek.isNotEmpty()) {
        while (currentWeek.size < 7) {
            currentWeek.add("")
        }
        daysList.add(currentWeek.toList())
    }

    return daysList
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

//@Preview(showBackground = true)
//@Composable
//fun AnalysisCalendarPreview() {
//    val navController = rememberNavController()
//    val viewModel =
//        HomeViewModel(CategoryRepository(AppDatabase.getDatabase(LocalContext.current))) // Provide your repository instance
//    AnalysisCalendar(navController, viewModel)
//}


