package com.example.elibraryproject.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List


data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

@Composable
fun BottomBar(navController: NavController) {

    val items = listOf(
        BottomNavItem(
            route = "home",
            label = "Home",
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            route = "katalog",
            label = "Katalog",
            icon = Icons.Default.List
        )
    )

    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()

        items.forEach { item ->
            val selected = navBackStackEntry.value?.destination?.route == item.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo("home")
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.label)
                },
                label = {
                    Text(item.label)
                }
            )
        }
    }
}
