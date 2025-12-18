package com.example.elibraryproject.ui.navigation

import BookRepository
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.elibraryproject.data.api.ApiClient
import com.example.elibraryproject.ui.screens.DetailScreen
import com.example.elibraryproject.ui.screens.KatalogScreen
import com.example.elibraryproject.ui.screens.LandingScreen
import com.example.elibraryproject.viewmodel.BookViewModel
import com.example.elibraryproject.viewmodel.BookViewModelFactory

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {

        composable("home") {
            LandingScreen(navController)
        }

        composable("katalog") {

            // 🔥 ViewModel HARUS dibuat di sini
            val viewModel: BookViewModel = viewModel(
                factory = BookViewModelFactory(
                    BookRepository(
                        ApiClient.openLibraryApi
                    )
                )
            )

            KatalogScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = "detail/{bookKey}",
            arguments = listOf(
                navArgument("bookKey") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val bookKey = backStackEntry.arguments
                ?.getString("bookKey")
                ?: ""

            DetailScreen(
                navController = navController,
                bookKey = bookKey
            )
        }
    }
}
