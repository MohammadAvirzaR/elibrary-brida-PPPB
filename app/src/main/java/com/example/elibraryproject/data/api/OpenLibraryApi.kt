package com.example.elibraryproject.data.api

import com.example.elibraryproject.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApi {

    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String
    ): SearchResponse
}
