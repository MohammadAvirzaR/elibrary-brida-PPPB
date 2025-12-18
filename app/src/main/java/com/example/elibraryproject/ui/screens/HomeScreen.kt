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
import com.example.elibraryproject.ui.components.BookCard
import com.example.elibraryproject.viewmodel.BookViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: BookViewModel
) {
    val books = viewModel.books
    val isLoading = viewModel.isLoading


    LaunchedEffect(Unit) {
        if (books.isEmpty()) {
            viewModel.search("education")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Rekomendasi Buku",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

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
