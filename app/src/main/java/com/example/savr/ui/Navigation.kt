package com.example.savr.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.savr.data.database.AppDatabase
import com.example.savr.data.repository.CategoryRepository
import com.example.savr.data.repository.UserRepository
import com.example.savr.ui.screens.Home
import com.example.savr.ui.screens.analysis.AnalysisCalendar
import com.example.savr.ui.screens.category.Categories
import com.example.savr.ui.screens.category.CategoryFilter
import com.example.savr.ui.screens.launchscreens.LaunchScreen
import com.example.savr.ui.screens.launchscreens.LaunchScreen2
import com.example.savr.ui.screens.login.Login
import com.example.savr.ui.screens.profile.Profile
import com.example.savr.ui.screens.profile.editprofile.EditProfile
import com.example.savr.ui.screens.profile.security.ChangePassword
import com.example.savr.ui.screens.profile.security.Security
import com.example.savr.ui.screens.profile.usersettings.DeleteAccount
import com.example.savr.ui.screens.profile.usersettings.NotificationSettings
import com.example.savr.ui.screens.signup.SignUp
import com.example.savr.ui.screens.transactions.AddandViewExpenses
import com.example.savr.ui.screens.transactions.Transactions
import com.example.savr.ui.viewmodels.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current // Get the context outside of remember

    // Initialize the ViewModel using the context
    val viewModel: HomeViewModel = remember {
        val database = AppDatabase.getDatabase(context)
        val categoryRepository = CategoryRepository(database)

        val userDao = database.userDao()
        val userRepository = UserRepository(userDao)

        HomeViewModel(categoryRepository, userRepository)
    }

    NavHost(navController = navController, startDestination = "login") {
        composable("launch_screen") { LaunchScreen(navController) }
        composable("launch_screen_2") { LaunchScreen2(navController) }

        composable("login") { Login(navController) }
        composable("signup") { SignUp(navController) }
        // composable("home") { Home(navController, viewModel, email, password) }

        // Add composable for the dynamic route
        composable(route = "home/{email}/{password}",
            arguments = listOf(navArgument("email") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType })) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            // Pass email and password to Home or a different composable
            Home(
                navController,
                viewModel,
                email,
                password
            ) // Assuming Home can handle these arguments
        }


        composable("analysis") { AnalysisCalendar(navController, viewModel) }
        composable("transactions") { Transactions(navController, viewModel) }
        composable("add_expense") { AddandViewExpenses(navController) }

        composable("categories") { Categories(navController) }
        composable(
            route = "category_filter/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            CategoryFilter(navController, category, viewModel)
        }

        composable("profile") { Profile(navController) }
        composable("edit_profile") { EditProfile(navController) }
        composable("security_settings") { Security(navController) }
        composable("change_password") { ChangePassword(navController) }
        composable("notification_settings") { NotificationSettings(navController) }
        composable("delete_account") { DeleteAccount(navController) }
    }
}

