package com.example.elibraryproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.elibraryproject.ui.screens.DetailScreen
import com.example.elibraryproject.ui.screens.KatalogScreen
import com.example.yourapp.ui.screens.HomeScreen
import com.example.elibraryproject.viewmodel.BookViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    bookViewModel: BookViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                navController = navController,
                viewModel = bookViewModel
            )
        }

        composable("katalog") {
            KatalogScreen(
                navController = navController,
                viewModel = bookViewModel
            )
        }

        composable("detail/{bookKey}") { backStackEntry ->
            DetailScreen(
                navController = navController,
                bookKey = backStackEntry.arguments?.getString("bookKey") ?: "",
                viewModel = bookViewModel
            )
        }
    }
}
