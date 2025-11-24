package com.example.elibraryproject.data.model

data class BookDoc(
    val title: String? = "",
    val author_name: List<String>? = emptyList(),
    val cover_i: Int? = null
) {
    val imageUrl: String
        get() = if (cover_i != null)
            "https://covers.openlibrary.org/b/id/${cover_i}-L.jpg"
        else
            "https://via.placeholder.com/200x300?text=No+Cover"

    val author: String
        get() = author_name?.firstOrNull() ?: "Unknown Author"
}
