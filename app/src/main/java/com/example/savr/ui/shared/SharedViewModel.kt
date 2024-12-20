package com.example.savr.ui.shared

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.savr.R
import com.example.savr.data.database.AppDatabase
import com.example.savr.data.database.model.Category
import com.example.savr.data.database.model.Expense
import com.example.savr.data.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewModel(application: Application, private val expenseRepository: ExpenseRepository) : AndroidViewModel(application){
    private val categoryDao = AppDatabase.getDatabase(application).categoryDao()
    private val _categoriesList = MutableStateFlow<List<Category>>(emptyList())
    val categoriesList = _categoriesList.asStateFlow()

    private val expenseDao = AppDatabase.getDatabase(application).expenseDao()
    val expensesList: Flow<List<Expense>> = expenseDao.getAllExpenses()

    init {
        viewModelScope.launch {
            categoryDao.getAllCategories().collect { categories ->
                if (categories.isEmpty()) {
                    prepopulateCategories() // Separate function for pre-population
                }
                _categoriesList.value = categories
            }
        }
    }

    private suspend fun prepopulateCategories() {
        val defaultCategories = listOf(
            Category(name = "Food", iconRes = R.drawable.food),
            Category(name = "Transport", iconRes = R.drawable.transport),
            Category(name = "Medicine", iconRes = R.drawable.medicine),
            Category(name = "Groceries", iconRes = R.drawable.groceries),
            Category(name = "Rent", iconRes = R.drawable.rent),
            Category(name = "Gifts", iconRes = R.drawable.gifts),
            Category(name = "Savings", iconRes = R.drawable.savings),
            Category(name = "Entertainment", iconRes = R.drawable.entertainment)
        )
        defaultCategories.forEach { category ->
            categoryDao.insertCategory(category)
        }
    }

    fun addCategory(categoryName: String, iconRes: Int) {
        viewModelScope.launch {
            categoryDao.insertCategory(Category(name = categoryName, iconRes = iconRes))
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            expenseRepository.insertExpense(expense) // Use the expenseRepository instance
        }
    }

    private val _userName = mutableStateOf("User") // Default value
    val userName: State<String> = _userName

    fun updateUserName(name: String) {
        _userName.value = name
    }
}

class SharedViewModelFactory(
    private val application: Application,
    private val expenseRepository: ExpenseRepository // Add expenseRepository as a parameter
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SharedViewModel(application, expenseRepository) as T // Pass expenseRepository to SharedViewModel
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

