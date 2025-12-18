package com.example.elibraryproject.viewmodel

import BookRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elibraryproject.data.model.BookDoc
import kotlinx.coroutines.launch

class BookViewModel(
    private val repository: BookRepository
) : ViewModel() {

    var books by mutableStateOf<List<BookDoc>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var searchQuery by mutableStateOf("")
        private set

    fun onQueryChange(query: String) {
        searchQuery = query
    }

    fun submitSearch() {
        if (searchQuery.isBlank()) return

        viewModelScope.launch {
            isLoading = true
            try {
                books = repository.searchBooks(searchQuery)
            } catch (e: Exception) {
                errorMessage = "Gagal memuat buku"
            } finally {
                isLoading = false
            }
        }
    }
}

