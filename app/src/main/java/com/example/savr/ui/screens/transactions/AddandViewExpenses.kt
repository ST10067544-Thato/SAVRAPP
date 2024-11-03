package com.example.savr.ui.screens.transactions

import android.app.Application
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.savr.R
import com.example.savr.data.database.AppDatabase
import com.example.savr.data.database.model.Expense
import com.example.savr.data.repository.ExpenseRepository
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.InputField
import com.example.savr.ui.logic.ScreenTopSection
import com.example.savr.ui.shared.SharedViewModel
import com.example.savr.ui.shared.SharedViewModelFactory
import kotlinx.coroutines.launch
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddandViewExpenses(navController: NavController) {
    val context = LocalContext.current.applicationContext as Application
    val viewModel: SharedViewModel = viewModel(factory = SharedViewModelFactory(context, ExpenseRepository(
        AppDatabase.getDatabase(context).expenseDao())))

    // Get the coroutine scope for launching coroutines
    val coroutineScope = rememberCoroutineScope()

    var amount by remember { mutableStateOf("") }
    var expenseTitle by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("") }
    var selectedCategoryId by remember { mutableStateOf(-1) }

    // Observe categoriesList for changes
    val categories by viewModel.categoriesList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF8D3C)) // Base orange background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 15.dp)
                .padding(horizontal = 36.dp)
        ) {
            ScreenTopSection(navController = navController,
                title = "Add an Expense",
                onBack = { navController.popBackStack() })
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFFFFFFF), RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .padding(top = 40.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp)
            ) {
                item {
                    InputFieldWithDatePicker(label = "Date",
                        selectedDate = selectedDate,
                        onDateSelected = { newDate -> selectedDate = newDate })
                }

                item {
                    InputField(
                        label = "Category",
                        value = selectedCategory,
                        placeholder = "Select the category",
                        onValueChange = { selectedCategory = it },
                        trailingIcon = {
                            IconButton(onClick = { expanded = !expanded }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_arrow_drop_down),
                                    contentDescription = "Dropdown",
                                    tint = Color(0xFFFF8D3C)
                                )
                            }
                        },
                        isCompact = true,
                        readOnly = true
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(Color(0xFFFFF4EC))
                    ) {
                        categories.forEach { category ->
                            DropdownMenuItem(text = {
                                Text(
                                    category.name,
                                    color = Color(0xFF093030)
                                )
                            },
                                onClick = {
                                    selectedCategory = category.name
                                    selectedCategoryId = category.id // Set the selected category ID
                                    expanded = false
                                },
                                modifier = Modifier.background(Color(0xFFFFF4EC))
                            )
                        }
                    }
                }

                item {
                    InputField(label = "Amount",
                        value = amount,
                        placeholder = "Please enter in an amount.",
                        onValueChange = { amount = it })
                }
                item {
                    InputField(label = "Expense Title",
                        value = expenseTitle,
                        placeholder = "Please enter a title.",
                        onValueChange = { expenseTitle = it })
                }
                item {
                    InputField(label = "Description",
                        value = description,
                        placeholder = "Enter description.",
                        onValueChange = { description = it })
                }
                item {
                    Button(
                        onClick = {
                            if (amount.isNotEmpty() && expenseTitle.isNotEmpty() && selectedCategoryId != -1) {
                                val newExpense = Expense(
                                    title = expenseTitle,
                                    amount = amount.toDouble(),
                                    date = selectedDate!!, // Non-null assertion since we checked for null
                                    categoryId = selectedCategoryId
                                )
                                // Launch the coroutine to call the suspend function
                                coroutineScope.launch {
                                    viewModel.addExpense(newExpense) // Call addExpense from SharedViewModel
                                    navController.popBackStack() // Navigate back after saving
                                }
                            }
                        },
                        modifier = Modifier
                            .padding(vertical = 20.dp, horizontal = 50.dp)
                            .fillMaxWidth()
                            .height(40.dp)
                            .clip(RoundedCornerShape(18.dp)),
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFFFF8D3C), contentColor = Color.White
                        )
                    ) {
                        Text(text = "Save", fontSize = 16.sp)
                    }
                }
            }
        }
        BottomNavBar(navController = navController, selectedRoute = "transactions")
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputFieldWithDatePicker(
    label: String, selectedDate: LocalDate?, onDateSelected: (LocalDate) -> Unit
) {
    val context = LocalContext.current
    var showDatePicker by remember { mutableStateOf(false) }

    Column {
        Text(
            label,
            color = Color(0xFF093030),
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 29.dp)
                .clip(shape = RoundedCornerShape(18.dp))
                .fillMaxWidth()
                .background(color = Color(0xFFFFF4EC), shape = RoundedCornerShape(18.dp))
                .padding(vertical = 10.dp, horizontal = 18.dp)
                .clickable { showDatePicker = true } // Make the row clickable
        ) {
            BasicTextField(
                value = selectedDate?.toString() ?: "Please select a date", // Display selected date or placeholder
                onValueChange = { /* Do nothing, date is set by date picker */ },
                textStyle = TextStyle(color = Color(0xFF093030), fontSize = 16.sp),
                modifier = Modifier.weight(1f),
                readOnly = true, // Make the text field read-only
            )
            IconButton(onClick = { showDatePicker = true }) { // Make icon clickable
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "Calendar",
                    tint = Color(0xFFFF8D3C)
                )
            }
        }
    }

    // Show the date picker dialog if showDatePicker is true
    if (showDatePicker) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        android.app.DatePickerDialog(
            context, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedLocalDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
                onDateSelected(selectedLocalDate) // Update selectedDate state
            }, year, month, day
        ).show()

        showDatePicker = false // Close the dialog after selection
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AddandViewExpensesPreview() {
    val navController = rememberNavController() // Create a NavController for preview

    // Set a sample date for preview purposes
    val sampleDate = LocalDate.now()

    AddandViewExpenses(navController = navController)
}

