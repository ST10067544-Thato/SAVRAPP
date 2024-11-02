package com.example.savr.data.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.savr.data.database.model.Expense
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AppDatabaseCallback : RoomDatabase.Callback() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        db.execSQL("INSERT INTO expenses (title, amount, date, categoryId) VALUES ('Groceries', 150.00, '$currentDate', 1)")
        db.execSQL("INSERT INTO expenses (title, amount, date, categoryId) VALUES ('Fuel', 80.50, '$currentDate', 2)")
        db.execSQL("INSERT INTO expenses (title, amount, date, categoryId) VALUES ('Restaurant', 200.00, '$currentDate', 3)")

        insertDummyData(db) // Call the function to insert dummy data
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        insertDummyData(db) // Call the function to insert dummy data
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertDummyData(db: SupportSQLiteDatabase) {
        val today = LocalDate.now()
        val yesterday = today.minusDays(1)
        val twoDaysAgo = today.minusDays(2)
        val threeDaysAgo = today.minusDays(3)
        val fourDaysAgo = today.minusDays(4)
        val fiveDaysAgo = today.minusDays(5)
        val sixDaysAgo = today.minusDays(6)

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        // Insert 10 imaginary data entries
        db.execSQL("INSERT INTO expenses (title, amount, date, categoryId) VALUES ('Coffee', 35.00, '${today.format(formatter)}', 1)")
        db.execSQL("INSERT INTO expenses (title, amount, date, categoryId) VALUES ('Movie Ticket', 120.00, '${yesterday.format(formatter)}', 3)")
        db.execSQL("INSERT INTO expenses (title, amount, date, categoryId) VALUES ('Train ticket', 60.00, '${twoDaysAgo.format(formatter)}', 2)")
        db.execSQL("INSERT INTO expenses (title, amount, date, categoryId) VALUES ('Book', 85.50, '${threeDaysAgo.format(formatter)}', 4)")
        db.execSQL("INSERT INTO expenses (title, amount, date, categoryId) VALUES ('Snacks', 45.00, '${fourDaysAgo.format(formatter)}', 1)")
        db.execSQL("INSERT INTO expenses (title, amount, date, categoryId) VALUES ('Data', 150.00, '${fiveDaysAgo.format(formatter)}', 5)")
        db.execSQL("INSERT INTO expenses (title, amount, date, categoryId) VALUES ('Gifts', 250.00, '${sixDaysAgo.format(formatter)}', 6)")
        db.execSQL("INSERT INTO expenses (title, amount, date, categoryId) VALUES ('Lunch', 75.00, '${today.format(formatter)}', 3)")
        db.execSQL("INSERT INTO expenses (title, amount, date, categoryId) VALUES ('Uber', 100.00, '${yesterday.format(formatter)}', 2)")
        db.execSQL("INSERT INTO expenses (title, amount, date, categoryId) VALUES ('Clothes', 300.00, '${threeDaysAgo.format(formatter)}', 7)")
    }
}