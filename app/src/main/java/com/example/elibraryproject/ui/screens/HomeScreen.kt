package com.example.yourapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.elibraryproject.AppHeader
import com.example.elibraryproject.ui.components.BookCard
import com.example.yourapp.data.dummyBooks

@Composable
fun HomeScreen() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // HEADER
        item { AppHeader() }

        // REKOMENDASI
        item {
            Text(
                text = "Rekomendasi Buku",
                style = MaterialTheme.typography.titleLarge
            )
        }

        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(dummyBooks) { book ->
                    BookCard(
                        book = book,
                        modifier = Modifier.width(160.dp)
                    )
                }
            }
        }

        // DAFTAR BUKU
        item {
            Text(
                text = "Daftar Buku",
                style = MaterialTheme.typography.titleLarge
            )
        }

        // --- GRID MANUAL 2 KOLUMN ---
        items(dummyBooks.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                for (book in rowItems) {
                    BookCard(
                        book = book,
                        modifier = Modifier
                            .weight(1f)
                            .height(260.dp)
                    )
                }

                // kalau jumlahnya ganjil → isi spacer
                if (rowItems.size == 1) {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                            .height(260.dp)
                    )
                }
            }
        }
    }
}
