package com.example.savr.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.savr.data.database.AppDatabase
import com.example.savr.data.database.model.Category
import com.example.savr.data.database.model.Expense
import com.example.savr.ui.logic.FilterType
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class CategoryRepository(private val database: AppDatabase) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getExpenses(filterType: FilterType): Flow<List<Expense>> {
        return when (filterType) {
            FilterType.DAILY -> database.expenseDao().getExpensesByDate(LocalDate.now())
            FilterType.WEEKLY -> database.expenseDao().getExpensesByDateRange(
                LocalDate.now().minus(7, ChronoUnit.DAYS),
                LocalDate.now()
            )
            FilterType.MONTHLY -> database.expenseDao().getExpensesByDateRange(
                LocalDate.now().withDayOfMonth(1),
                LocalDate.now()
            )
            else -> database.expenseDao().getAllExpenses() // Default to all expenses
        }
    }

    fun getCategories(): Flow<List<Category>> {
        return database.categoryDao().getAllCategories()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun insertDummyTransactions() {
        val currentDate = LocalDate.now()
        val expenses = listOf(
            Expense(title = "Groceries", amount = 150.00, date = currentDate, categoryId = 1),
            Expense(title = "Fuel", amount = 80.50, date = currentDate, categoryId = 2),
            Expense(title = "Restaurant", amount = 200.00, date = currentDate, categoryId = 3)
        )
        database.expenseDao().insertAll(expenses)
    }
}