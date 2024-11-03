package com.example.savr.data.repository

import com.example.savr.data.database.dao.ExpenseDao
import com.example.savr.data.database.model.Expense

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    // ... other code ...

    suspend fun insertExpense(expense: Expense) {
        expenseDao.insertExpense(expense)
    }
}