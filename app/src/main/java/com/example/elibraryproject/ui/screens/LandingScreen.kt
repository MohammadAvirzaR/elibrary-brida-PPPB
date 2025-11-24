package com.example.elibraryproject.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elibraryproject.ui.components.AppHeader
import com.example.elibraryproject.ui.components.BookCard
import com.example.elibraryproject.data.dummyBooks

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(navController: NavHostController) {
    var query by remember { mutableStateOf("") }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // HEADER sebagai item full-width
        item(span = { GridItemSpan(2) }) {
            AppHeader(
                searchQuery = query,
                onQueryChange = {query = it},
                onLogoClick = {navController.navigate("home")},
                onKatalogClick = {navController.navigate("katalog")}
            )
        }

        item(span = { GridItemSpan(2) }) {
            Spacer(modifier = Modifier.height(20.dp))
        }

        // REKOMENDASI BUKU TITLE
        item(span = { GridItemSpan(2) }) {
            Text(
                text = "Rekomendasi Buku",
                style = MaterialTheme.typography.titleLarge
            )
        }

        item(span = { GridItemSpan(2) }) {
            Spacer(modifier = Modifier.height(12.dp))
        }

        // REKOMENDASI HORIZONTAL
        item(span = { GridItemSpan(2) }) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(dummyBooks.take(5)) { book ->
                    BookCard(
                        book = book,
                        onClick = { navController.navigate("detail/${book.key}") }
                    )
                }

            }
        }

        item(span = { GridItemSpan(2) }) {
            Spacer(modifier = Modifier.height(24.dp))
        }

        // DAFTAR BUKU TITLE
        item(span = { GridItemSpan(2) }) {
            Text(
                text = "Daftar Buku",
                style = MaterialTheme.typography.titleLarge
            )
        }

        item(span = { GridItemSpan(2) }) {
            Spacer(modifier = Modifier.height(12.dp))
        }

        // GRID BUKU
        items(dummyBooks) { book ->
            BookCard(
                book = book,
                onClick = { navController.navigate("detail/${book.key}") }
            )
        }
    }
}