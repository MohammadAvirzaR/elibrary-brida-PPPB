package com.example.elibraryproject.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elibraryproject.data.model.BookDoc
import com.example.elibraryproject.data.repository.BookRepository
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

    fun search(query: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val response = repository.searchBooks(query)
                books = response.docs
            } catch (e: Exception) {
                errorMessage = "Gagal memuat buku"
            } finally {
                isLoading = false
            }
        }
    }
}
