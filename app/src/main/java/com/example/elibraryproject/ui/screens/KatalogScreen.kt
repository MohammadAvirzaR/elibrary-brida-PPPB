package com.example.elibraryproject.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.elibraryproject.ui.components.BookCard
import com.example.elibraryproject.viewmodel.BookViewModel

@Composable
fun KatalogScreen(
    navController: NavController,
    viewModel: BookViewModel
) {
    val books = viewModel.books
    val isLoading = viewModel.isLoading

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(12.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(books) { book ->
            BookCard(
                book = book,
                onClick = {
                    val rawKey = book.key ?: return@BookCard
                    val cleanKey = rawKey.removePrefix("/works/")
                    navController.navigate("detail/$cleanKey")
                }
            )
        }
    }
}
