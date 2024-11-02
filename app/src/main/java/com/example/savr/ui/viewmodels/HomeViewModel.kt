package com.example.savr.ui.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savr.data.database.model.Expense
import com.example.savr.data.repository.CategoryRepository
import com.example.savr.ui.logic.FilterType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import java.time.DayOfWeek
import java.time.LocalDate

class HomeViewModel(private val repository: CategoryRepository) : ViewModel() {
    private val _selectedFilter = MutableStateFlow(FilterType.DAILY) // Initialize with DAILY
    val selectedFilter: StateFlow<FilterType> = _selectedFilter.asStateFlow() // Update this line

    @RequiresApi(Build.VERSION_CODES.O)
    val expenses = selectedFilter.flatMapLatest { filterType ->
        repository.getExpenses(filterType)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val categories = repository.getCategories().stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(), emptyList()
        )

    fun updateFilter(filterType: FilterType) {
        _selectedFilter.value = filterType
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getExpensesForToday(): List<Expense> {
        val today = LocalDate.now()
        return expenses.value.filter { it.date == today }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getExpensesForWeek(): List<Expense> {
        val today = LocalDate.now()
        val startOfWeek = today.with(DayOfWeek.MONDAY)
        val endOfWeek = today.with(DayOfWeek.SUNDAY)
        return expenses.value.filter { it.date in startOfWeek..endOfWeek }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getExpensesForMonth(): List<Expense> {
        val today = LocalDate.now()
        val startOfMonth = today.withDayOfMonth(1)
        val endOfMonth = today.withDayOfMonth(today.lengthOfMonth())
        return expenses.value.filter { it.date in startOfMonth..endOfMonth }
    }

    init {
        // Trigger initial data load with the default filter (DAILY)
        updateFilter(FilterType.DAILY)
    }
}