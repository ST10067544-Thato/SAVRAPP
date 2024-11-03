package com.example.savr.ui.screens.category

import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.savr.R
import com.example.savr.data.database.AppDatabase
import com.example.savr.data.database.model.Category
import com.example.savr.data.repository.ExpenseRepository
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.ScreenTopSection
import com.example.savr.ui.shared.SharedViewModel
import com.example.savr.ui.shared.SharedViewModelFactory

@Composable
fun Categories(navController: NavController) {
    val context = LocalContext.current.applicationContext as Application
    val viewModel: SharedViewModel = viewModel(factory = SharedViewModelFactory(context, ExpenseRepository(
        AppDatabase.getDatabase(context).expenseDao())))// Correctly instantiate the ViewModel

    val categories by viewModel.categoriesList.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF8D3C))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 20.dp)
                .padding(horizontal = 36.dp)
        ) {
            ScreenTopSection(navController = navController,
                title = "Categories",
                onBack = { navController.popBackStack() })
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White, RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .verticalScroll(rememberScrollState())
                .padding(top = 40.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                val categoriesWithAddNew =
                    categories + listOf(Category(name = "Add New", iconRes = R.drawable.more))
                val chunkedCategories = categoriesWithAddNew.chunked(3)

                for (rowCategories in chunkedCategories) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .fillMaxWidth()
                    ) {
                        for (category in rowCategories) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 5.dp)
                            ) {
                                OutlinedButton(
                                    onClick = {
                                        if (category.name != "Add New") {
                                            navController.navigate("category_filter/${category.name}")
                                        } else {
                                            showDialog = true
                                        }
                                    },
                                    border = BorderStroke(0.dp, Color.Transparent),
                                    colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                                    contentPadding = PaddingValues(),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(25.dp))
                                        .width(120.dp)
                                        .height(120.dp)
                                        .background(Color(0xFF6CB5FD), RoundedCornerShape(25.dp))
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Image(
                                            painter = painterResource(id = category.iconRes),
                                            contentDescription = category.name,
                                            modifier = Modifier
                                                .padding(top = 16.dp)
                                                .size(60.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = category.name,
                                    color = Color(0xFF093030),
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        BottomNavBar(navController = navController, selectedRoute = "categories")
    }
    if (showDialog) {
        AddNewCategory(onDismiss = { showDialog = false }, onSave = { newCategoryName ->
            viewModel.addCategory(newCategoryName, R.drawable.more)
            showDialog = false
        })
    }
}


@Preview(showBackground = true)
@Composable
fun CategoriesPreview() {
    val navController = rememberNavController()// Create a NavController for preview
    Categories(navController)
}


