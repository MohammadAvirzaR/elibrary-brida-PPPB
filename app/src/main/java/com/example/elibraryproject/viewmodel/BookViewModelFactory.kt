import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.elibraryproject.data.repository.BookRepository
import com.example.elibraryproject.ui.viewmodel.BookViewModel

class BookViewModelFactory(
    private val repository: BookRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            return BookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
