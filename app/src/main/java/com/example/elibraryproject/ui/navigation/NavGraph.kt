package com.example.elibraryproject.ui.navigation

import BookRepository
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.elibraryproject.data.api.OpenLibraryApi

import com.example.elibraryproject.ui.screens.KatalogScreen
import com.example.elibraryproject.viewmodel.BookViewModel
import com.example.elibraryproject.viewmodel.BookViewModelFactory
import com.example.yourapp.ui.screens.HomeScreen
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AppNavGraph(navController: NavHostController) {

    // 🔹 Retrofit
    val api = Retrofit.Builder()
        .baseUrl("https://openlibrary.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OpenLibraryApi::class.java)

    // 🔹 Repository
    val repository = BookRepository(api)

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen()
        }

        composable("katalog") {

            // 🔥 INI KUNCI UTAMA
            val viewModel: BookViewModel = viewModel(
                factory = BookViewModelFactory(repository)
            )

            KatalogScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
