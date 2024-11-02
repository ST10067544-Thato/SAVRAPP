package com.example.savr.ui.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.savr.R
import com.example.savr.data.database.AppDatabase
import com.example.savr.data.database.model.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    private val categoryDao = AppDatabase.getDatabase(application).categoryDao()
    private val _categoriesList = MutableStateFlow<List<Category>>(emptyList())
    val categoriesList = _categoriesList.asStateFlow()

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
}

class SharedViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SharedViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

