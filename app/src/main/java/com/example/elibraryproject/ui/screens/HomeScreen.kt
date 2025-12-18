package com.example.yourapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.elibraryproject.ui.components.AppHeader
import com.example.elibraryproject.ui.components.BookCard
import com.example.elibraryproject.ui.components.SearchBar
import com.example.elibraryproject.viewmodel.BookViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: BookViewModel
) {
    val books = viewModel.books
    val isLoading = viewModel.isLoading
    val query = viewModel.searchQuery





    LaunchedEffect(Unit) {
        if (books.isEmpty()) {
            viewModel.onQueryChange("education")
            viewModel.submitSearch()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AppHeader(
            searchQuery = viewModel.searchQuery,
            onQueryChange = viewModel::onQueryChange,
            onSearch = viewModel::submitSearch,
            onKatalogClick = {
                navController.navigate("katalog")
            }

        )








        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Column
        }

        if (books.isEmpty()) {
            Text("Tidak ada data buku")
            return@Column
        }

        LazyColumn {
            items(books.take(5)) { book ->
                BookCard(
                    book = book,
                    onClick = {
                        val rawKey = book.key ?: return@BookCard
                        val cleanKey = rawKey.removePrefix("/works/")
                        navController.navigate("detail/$cleanKey")
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}
