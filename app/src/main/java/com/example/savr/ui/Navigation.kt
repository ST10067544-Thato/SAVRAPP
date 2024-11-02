package com.example.savr.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("launch_screen") { LaunchScreen(navController) }
        composable("launch_screen_2") { LaunchScreen2(navController) }

        composable("login") { Login(navController) } // Add Login route
        composable("signup") { SignUp(navController) } // Add SignUp route
        composable("home") { Home(navController) } // Add the Home route
        composable("analysis") { AnalysisCalendar(navController) } // Add Analysis route
        composable("transactions") { Transactions(navController) } // Add Transactions route
        composable("add_expense") { AddandViewExpenses(navController) } // Add AddandViewExpenses route

        composable("categories") { Categories(navController) } // Add Categories route
        composable(
            route = "category_filter/{category}", // Define route with argument
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            CategoryFilter(navController, category) // Pass category to CategoryFilter
        }

        composable("profile") { Profile(navController) } // Add Profile route
        composable("edit_profile") { EditProfile(navController) } // Add Edit Profile route
        composable("security_settings") { Security(navController) } // Add Profile Security Settings route
        composable("change_password") { ChangePassword(navController) } // Add Change Profile Password route
        composable("notification_settings") { NotificationSettings(navController) } // Add Notification Settings route
//        composable("language_settings") { LanguageSettings() } // Add Language Settings route
        composable("delete_account") { DeleteAccount(navController) } // Add Delete Account route


    }
}