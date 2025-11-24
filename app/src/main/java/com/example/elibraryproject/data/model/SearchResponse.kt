package com.example.elibraryproject.data.model

data class SearchResponse(
    val numFound: Int = 0,
    val start: Int = 0,
    val docs: List<BookDoc> = emptyList()
)
