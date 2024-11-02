package com.example.savr.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.savr.R
import com.example.savr.data.database.AppDatabase
import com.example.savr.data.repository.CategoryRepository
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.CustomNotificationBar
import com.example.savr.ui.logic.DisplayExpense
import com.example.savr.ui.logic.FilterButton
import com.example.savr.ui.logic.FilterType
import com.example.savr.ui.viewmodels.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(navController: NavController, viewModel: HomeViewModel) {
    val expenses by viewModel.expenses.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val selectedFilter by viewModel.selectedFilter.collectAsState()

    // Filter expenses based on selectedFilter
    val filteredExpenses = when (selectedFilter) {
        FilterType.DAILY -> viewModel.getExpensesForToday()
        FilterType.WEEKLY -> viewModel.getExpensesForWeek()
        FilterType.MONTHLY -> viewModel.getExpensesForMonth()
        FilterType.SPENDS -> TODO()
        FilterType.CATEGORIES -> TODO()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF8D3C)) // Base orange background
    ) {
        // Notification Bar and Title
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 36.dp)
        ) {
            CustomNotificationBar()

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 45.dp, start = 16.dp)
                    .fillMaxWidth()
            ) {
                //personalized welcome label with name dynamic on logged in user
                Text(
                    "Hi John, Welcome Back",
                    color = Color(0xFFFFFFFF),
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f) // Allow text to take available space
                )
                //little notification button on the far right of the welcome label
                IconButton(
                    onClick = { /* Handle click for icon 2 */ },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_notifications), // Load the analysis icon
                        contentDescription = "notification", modifier = Modifier.size(28.dp)
                    )
                }

            }
        }
        // Section which will dynamically display summary of logged in user's balance + expenses for the month
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 16.dp, start = 60.dp, end = 60.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.width(115.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.income),
                            contentDescription = "income",
                            modifier = Modifier.size(12.dp)
                        )
                    }
                    Text(
                        "Current Balance",
                        color = Color(0xFFFFFFFF),
                        fontSize = 12.sp,
                    )
                }
                Text(
                    "R7,783.00",
                    color = Color(0xFFFFF4EC),
                    fontSize = 24.sp,
                )
            }
            Column(
                modifier = Modifier
                    .width(1.dp)
                    .height(42.dp)
                    .background(
                        color = Color(0xFFFFF4EC),
                    )
            ) {}
            Column(
                modifier = Modifier.width(118.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 7.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .padding(horizontal = 3.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.expense),
                            contentDescription = "expense",
                            modifier = Modifier.size(12.dp)
                        )

                    }
                    Text(
                        "Total Expenses",
                        color = Color(0xFFFFFFFF),
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Text(
                    "-R1,187.40",
                    color = Color(0xFF0068FF),
                    fontSize = 24.sp,
                )
            }
        }
        // progress bar that will be dynamic and fill with black until 100% depending on progress of saving goal set by logged user
        val progress = 0.3f // Replace with your actual progress value (0.0f to 1.0f)

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 15.dp, start = 50.dp, end = 50.dp)
                .clip(shape = RoundedCornerShape(13.dp))
                .fillMaxWidth()
                .background(
                    color = Color(0xFF052224), shape = RoundedCornerShape(13.dp)
                )
                .padding(start = 22.dp)
        ) {
            Text(
                text = "${(progress * 100).toInt()}%", // Display progress percentage
                color = Color(0xFFFFF4EC),
                fontSize = 12.sp,
            )
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(13.dp))
                    .width(261.dp)
                    .background(
                        color = Color(0xFFFFF4EC), shape = RoundedCornerShape(13.dp)
                    )
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                // Progress indicator
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress) // Fill width based on progress
                        .height(IntrinsicSize.Min)
                        .background(
                            color = Color(0xFF052224), // Progress bar color
                            shape = RoundedCornerShape(13.dp)
                        )
                )

                // Text inside progress bar
                Text(
                    text = "R2,000.00", // Replace with your actual target value
                    color = if (progress > 0.5f) Color(0xFFFF8D3C) else Color(0xFF052224), // Adjust text color for contrast
                    fontSize = 13.sp,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 23.dp)
                )
            }
        }
        //little motivation message which will display and change dynamically based on user saving goal progress
        Row(
            horizontalArrangement = Arrangement.Center, // Center horizontally
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth()
        ) {
            Row( // Wrap emoji and text in an inner Row
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.wrapContentSize(Alignment.Center) // Center inner Row
            ) {
                //FIRE EMOJI
                Image(
                    painter = painterResource(id = R.drawable.fire_emoji),
                    contentDescription = "income",
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    "30% of your goal acheived, looks good!",
                    color = Color(0xFFFFFFFF),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }
        }

        // Curved white layered page for Home elements
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    Color.White, shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                )
                //.verticalScroll(rememberScrollState())
        ) {
            Column( // Wrap content in a Column
                modifier = Modifier
                    .padding(top = 40.dp)
                    .padding(horizontal = 36.dp)
                    .fillMaxWidth()
            ) {
                //little cute modern and sleek weekly summary box section with a summary of the user's
                // previous week's expenses,and their highest spent category and their current
                // savings progress on one their saved goals like car but this one will have a
                //progress circle around the category symbol which will fill based on progress
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .height(130.dp)
                        .clip(shape = RoundedCornerShape(31.dp))
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFFF8D3C)
                        )
                        .padding(vertical = 11.dp)
                ) {
                    Column(
                        modifier = Modifier.width(220.dp)
                    ) {
                        // summary section of the user's previous week's expenses,and their highest spent category and their current
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(bottom = 12.dp, start = 8.dp, end = 2.dp)
                                .fillMaxWidth()
                        ) {
                            //revenue icon
                            Image(
                                painter = painterResource(id = R.drawable.revenue),
                                contentDescription = "income",
                                modifier = Modifier.size(30.dp)
                            )

                            Column(
                                modifier = Modifier.width(113.dp)
                            ) {
                                Text(
                                    "Revenue Last Week",
                                    color = Color(0xFFFFFFFF),
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(start = 6.dp, bottom = 6.dp)
                                )
                                Text(
                                    "R4,000.00",
                                    color = Color(0xFFFFFFFF),
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(start = 6.dp)
                                )
                            }
                        }
                        //horizontal seperator
                        Column(
                            modifier = Modifier
                                .padding(bottom = 12.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color(0xFFFFF4EC),
                                )
                                .height(2.dp)
                                .fillMaxWidth()
                        ) {}
                        //highest spent category placeholder which will be dynamic per user (it's food in this case)
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 2.dp)
                                .fillMaxWidth()
                        ) {
                            //highest spent category icon
                            Image(
                                painter = painterResource(id = R.drawable.food),
                                contentDescription = "income",
                                modifier = Modifier.size(30.dp)
                            )
                            Column(
                                modifier = Modifier.width(113.dp)
                            ) {
                                Text(
                                    "Food Last Week",
                                    color = Color(0xFFFFFFFF),
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(start = 6.dp, bottom = 6.dp)
                                )
                                Text(
                                    "-R100.00",
                                    color = Color(0xFF0068FF),
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(start = 6.dp)
                                )
                            }
                        }
                    }
                }


                //Recent transactions section which will display recent transactions filtered based
                // on the period the user chooses from the button: "Daily", "Weekly" or "Monthly"
                Row(
                    horizontalArrangement = Arrangement.Center, // Center horizontally
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFFFF4EC), shape = RoundedCornerShape(22.dp)
                        )
                        .padding(vertical = 1.dp)
                ) {
                    FilterButton(text = "Daily",
                        isSelected = selectedFilter == FilterType.DAILY,
                        onClick = { viewModel.updateFilter(FilterType.DAILY) })

                    FilterButton(text = "Weekly",
                        isSelected = selectedFilter == FilterType.WEEKLY,
                        onClick = { viewModel.updateFilter(FilterType.WEEKLY) })

                    FilterButton(text = "Monthly",
                        isSelected = selectedFilter == FilterType.MONTHLY,
                        onClick = { viewModel.updateFilter(FilterType.MONTHLY) })
                }
                // Display the Conditional content based on selected filter button
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()) // Add this line
                ) {
                    for (expense in filteredExpenses) { // Use filteredExpenses here
                        val category = categories.find { it.id == expense.categoryId }
                        DisplayExpense(navController, expense, category)
                    }
                }
            }
        }
        BottomNavBar(navController = navController, selectedRoute = "home")
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    val viewModel =
        HomeViewModel(CategoryRepository(AppDatabase.getDatabase(LocalContext.current))) // Provide your repository instance
    Home(navController, viewModel)
}

