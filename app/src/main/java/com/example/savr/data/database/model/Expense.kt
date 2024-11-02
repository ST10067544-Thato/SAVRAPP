package com.example.savr.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val amount: Double,
    val date: LocalDate,
    val categoryId: Int,
    val newColumn: String? = null // Add new column here
)