package com.example.elibraryproject.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.elibraryproject.ui.components.AppHeader
import com.example.yourapp.data.dummyBooks

@Composable
fun KatalogScreen(navController: NavController) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // HEADER SEBAGAI ITEM FULL WIDTH
        item(span = { GridItemSpan(2) }) {
            AppHeader(
                onLogoClick = { navController.navigate("home") },
                onKatalogClick = { navController.navigate("katalog") }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // DAFTAR BUKU
        items(dummyBooks) { book ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable {
                        navController.navigate("detail/${book.id}")
                    }
            ) {
                AsyncImage(
                    model = book.imageUrl,
                    contentDescription = book.title,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
