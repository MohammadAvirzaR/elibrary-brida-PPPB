package com.example.elibraryproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.elibraryproject.ui.screens.DetailScreen
import com.example.elibraryproject.ui.screens.KatalogScreen
import com.example.elibraryproject.ui.screens.LandingScreen

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
            KatalogScreen(navController)
        }

        // DETAIL PAKAI bookKey (String)
        composable(
            route = "detail/{bookKey}",
            arguments = listOf(
                navArgument("bookKey") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val bookKey = backStackEntry.arguments?.getString("bookKey") ?: ""
            DetailScreen(
                navController = navController,
                bookKey = bookKey
            )
        }
    }
}
