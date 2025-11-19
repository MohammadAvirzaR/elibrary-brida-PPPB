package com.example.elibraryproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.elibraryproject.ui.screens.KatalogScreen
import com.example.elibraryproject.ui.screens.LandingScreen
import com.example.yourapp.ui.screens.HomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            LandingScreen(navController)
        }

        composable("katalog") {
            KatalogScreen(navController)
        }
    }

}
