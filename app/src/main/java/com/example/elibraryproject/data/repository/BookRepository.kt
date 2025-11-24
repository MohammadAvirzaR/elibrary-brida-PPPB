import com.example.elibraryproject.data.api.OpenLibraryApi
import com.example.elibraryproject.data.model.BookDoc

class BookRepository(
    private val api: OpenLibraryApi
) {

    suspend fun searchBooks(query: String): List<BookDoc> {
        val response = api.searchBooks(query)
        return response.docs
    }
}
