package com.example.elibraryproject.data.repository

import BookDoc
import OpenLibraryApi

class BookRepository (
    private val api:OpenLibraryApi

){
    suspend fun searchBooks(query: String): List<BookDoc> {
        val response = ApiClient.api.searchBooks(query)
        return response.docs ?: emptyList()
    }
}

