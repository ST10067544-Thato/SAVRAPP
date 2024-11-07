package com.example.savr.data.database

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.savr.data.database.dao.CategoryDao
import com.example.savr.data.database.dao.ExpenseDao
import com.example.savr.data.database.dao.UserDao
import com.example.savr.data.database.model.Category
import com.example.savr.data.database.model.Expense
import com.example.savr.data.database.model.User
import com.example.savr.util.DateConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Category::class, Expense::class, User::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "savr_database"
                ).addCallback(AppDatabaseCallback()) // Add callback here
                    .fallbackToDestructiveMigration() // Allow destructive migration, the existing data will be lost for testing.
                    .build()
                INSTANCE = instance
                prepopulateDatabase(context) // This will insert dummy data
                instance
            }
        }

        @SuppressLint("RestrictedApi")
        private fun prepopulateDatabase(context: Context) {
            val database = getDatabase(context)
            val userDao = database.userDao()

            CoroutineScope(Dispatchers.IO).launch {
                val dummyUser = User(
                    email = "opsc7311grm@gmail.com", password = "Shmurda1!", name = "Thato Sebelemetja"
                )
                userDao.insertUser(dummyUser)
                Log.d("AppDatabase", "Dummy user inserted: $dummyUser") // Add this line
            }
        }
    }
}