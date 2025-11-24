data class SearchResponse(
    val numFound: Int = 0,
    val start: Int = 0,
    val docs: List<BookDoc> = emptyList()
)

data class BookDoc(
    val title: String?,
    val author_name: List<String>?,
    val cover_i: Int?,
    val first_publish_year: Int?,
    val key: String? // "/works/OL12345W"
)


