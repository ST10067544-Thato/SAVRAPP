package com.example.savr.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.savr.data.database.dao.CategoryDao
import com.example.savr.data.database.dao.ExpenseDao
import com.example.savr.data.database.model.Category
import com.example.savr.data.database.model.Expense
import com.example.savr.util.DateConverter
import java.time.LocalDate

@Database(entities = [Category::class, Expense::class], version = 2)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "savr_database"
                )
                    .addCallback(AppDatabaseCallback()) // Add callback here
                    .fallbackToDestructiveMigration() // Allow destructive migration, the existing data will be lost for testing.
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}