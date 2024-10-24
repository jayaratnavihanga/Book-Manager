package com.example.bookmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BookRepository

    val allBooks: LiveData<List<Book>>

    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        allBooks = repository.allBooks
    }

    fun insertBook(book: Book) = viewModelScope.launch {
        try {
            repository.insert(book)
        } catch (e: Exception) {
            // Handle exception (e.g., log or show error message)
        }
    }

    fun insertMockData() = viewModelScope.launch {
        val mockBooks = listOf(
            Book(title = "1984", author = "George Orwell", genre = "Dystopian", isbn = "123456789", publishedDate = "1949"),
            Book(title = "To Kill a Mockingbird", author = "Harper Lee", genre = "Fiction", isbn = "987654321", publishedDate = "1960")
        )
        repository.insertAll(mockBooks)
    }
}
