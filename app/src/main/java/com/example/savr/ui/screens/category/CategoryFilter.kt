package com.example.savr.ui.screens.category

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.savr.data.database.AppDatabase
import com.example.savr.data.repository.CategoryRepository
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.EmptyState
import com.example.savr.ui.logic.ScreenTopSection
import com.example.savr.ui.screens.transactions.TransactionSection
import com.example.savr.ui.viewmodels.HomeViewModel

@SuppressLint("NewApi")
@Composable
fun CategoryFilter(navController: NavController, categoryName: String, viewModel: HomeViewModel) {
    val expenses by viewModel.expenses.collectAsState()
    val categories by viewModel.categories.collectAsState()

    // Filter expenses by category name
    val filteredExpenses = expenses.filter { expense ->
        categories.find { it.id == expense.categoryId }?.name == categoryName
    }

    // Group filtered expenses by month
    val expensesByMonth = filteredExpenses.groupBy { it.date.month }

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
            ScreenTopSection(navController = navController,
                title = categoryName,
                onBack = { navController.popBackStack() })
        }

        // White layered page with LazyColumn
        Box(
            modifier = Modifier
                .fillMaxSize() // Fill the remaining space
                .weight(1f)
                .background(Color(0xFFFFFFFF), RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .padding(top = 26.dp)
        ) {
            if (filteredExpenses.isEmpty()) {
                EmptyState(message = "No transactions for this category")
            } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize() // Fill the Box
                    .padding(bottom = 70.dp) // Add bottom padding for BottomNavBar
            ) {
                expensesByMonth.forEach { (month, expensesForMonth) ->
                    item {
                        val monthName = month.name // Get month name directly from Month enum
                        TransactionSection(
                            monthName.lowercase().replaceFirstChar { it.titlecase() },
                            expensesForMonth,
                            categories,
                            navController
                        )
                    }
                }
            }}
        }
        BottomNavBar(
            navController = navController, selectedRoute = "categories"
        ) // Place the Bottom Navigation Bar here to keep it visible
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CategoryFilterPreview() {
//    val navController = rememberNavController()
//    val viewModel =
//        HomeViewModel(CategoryRepository(AppDatabase.getDatabase(LocalContext.current))) // Provide your repository instance
//    CategoryFilter(
//        navController,
//        categoryName = "Groceries",
//        viewModel = viewModel
//    ) // Pass a sample category name and viewModel
//}