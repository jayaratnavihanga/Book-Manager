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

    // Method to insert a book
    fun insertBook(book: Book) = viewModelScope.launch {
        try {
            repository.insert(book)
        } catch (e: Exception) {
            // Handle exception (e.g., log or show error message)
        }
    }

    // Method to clear the books table
    fun clearDatabase() = viewModelScope.launch {
        try {
            repository.clearAllBooks()
        } catch (e: Exception) {
            // Handle exception (e.g., log or show error message)
        }
    }

    fun updateBook(book: Book) = viewModelScope.launch {
        try {
            repository.update(book)
        } catch (e: Exception) {
            // Handle exception (e.g., log or show error message)
        }
    }


    // Method to insert mock data if the database is empty
    fun insertMockData() = viewModelScope.launch {
        try {
            // Check if the database already has books
            val existingBooks = repository.getBookCount()
            if (existingBooks == 0) {
                val mockBooks = generateMockBooks()
                repository.insertAll(mockBooks)
            }
        } catch (e: Exception) {
            // Handle exception (e.g., log or show error message)
        }
    }

    // Generate a list of 100 mock books
    private fun generateMockBooks(): List<Book> {
        return listOf(
            Book(title = "1984", author = "George Orwell", genre = "Dystopian", isbn = "123456789", publishedDate = "1949"),
            Book(title = "To Kill a Mockingbird", author = "Harper Lee", genre = "Fiction", isbn = "987654321", publishedDate = "1960"),
            Book(title = "The Great Gatsby", author = "F. Scott Fitzgerald", genre = "Classic", isbn = "192837465", publishedDate = "1925"),
            Book(title = "Brave New World", author = "Aldous Huxley", genre = "Dystopian", isbn = "564738291", publishedDate = "1932"),
            Book(title = "The Catcher in the Rye", author = "J.D. Salinger", genre = "Classic", isbn = "102938475", publishedDate = "1951"),
            Book(title = "The Hobbit", author = "J.R.R. Tolkien", genre = "Fantasy", isbn = "847362514", publishedDate = "1937"),
            Book(title = "Pride and Prejudice", author = "Jane Austen", genre = "Romance", isbn = "918273645", publishedDate = "1813"),
            Book(title = "Moby Dick", author = "Herman Melville", genre = "Adventure", isbn = "564738920", publishedDate = "1851"),
            Book(title = "War and Peace", author = "Leo Tolstoy", genre = "Historical", isbn = "472819374", publishedDate = "1869"),
            Book(title = "Crime and Punishment", author = "Fyodor Dostoevsky", genre = "Philosophical", isbn = "192837465", publishedDate = "1866"),
            Book(title = "Jane Eyre", author = "Charlotte Bronte", genre = "Romance", isbn = "123459876", publishedDate = "1847"),
            Book(title = "The Lord of the Rings", author = "J.R.R. Tolkien", genre = "Fantasy", isbn = "453627189", publishedDate = "1954"),
            Book(title = "The Alchemist", author = "Paulo Coelho", genre = "Fiction", isbn = "837264592", publishedDate = "1988"),
            Book(title = "Ulysses", author = "James Joyce", genre = "Modernist", isbn = "645382910", publishedDate = "1922"),
            Book(title = "The Odyssey", author = "Homer", genre = "Epic", isbn = "293847165", publishedDate = "800 BC"),
            Book(title = "The Iliad", author = "Homer", genre = "Epic", isbn = "839275640", publishedDate = "800 BC"),
            Book(title = "Les Misérables", author = "Victor Hugo", genre = "Historical", isbn = "472093827", publishedDate = "1862"),
            Book(title = "Don Quixote", author = "Miguel de Cervantes", genre = "Adventure", isbn = "678293847", publishedDate = "1605"),
            Book(title = "The Divine Comedy", author = "Dante Alighieri", genre = "Epic", isbn = "573829104", publishedDate = "1320"),
            Book(title = "A Tale of Two Cities", author = "Charles Dickens", genre = "Historical", isbn = "182937465", publishedDate = "1859"),
            Book(title = "Frankenstein", author = "Mary Shelley", genre = "Science Fiction", isbn = "839201746", publishedDate = "1818"),
            Book(title = "Dracula", author = "Bram Stoker", genre = "Horror", isbn = "837465920", publishedDate = "1897"),
            Book(title = "The Shining", author = "Stephen King", genre = "Horror", isbn = "192837564", publishedDate = "1977"),
            Book(title = "It", author = "Stephen King", genre = "Horror", isbn = "837465102", publishedDate = "1986"),
            Book(title = "A Brief History of Time", author = "Stephen Hawking", genre = "Science", isbn = "837465293", publishedDate = "1988"),
            Book(title = "Meditations", author = "Marcus Aurelius", genre = "Philosophy", isbn = "746382910", publishedDate = "180"),
            Book(title = "Sapiens", author = "Yuval Noah Harari", genre = "Non-fiction", isbn = "928374651", publishedDate = "2011"),
            Book(title = "Educated", author = "Tara Westover", genre = "Memoir", isbn = "647382910", publishedDate = "2018"),
            Book(title = "Becoming", author = "Michelle Obama", genre = "Memoir", isbn = "647382920", publishedDate = "2018"),
            Book(title = "The Road", author = "Cormac McCarthy", genre = "Post-apocalyptic", isbn = "837465102", publishedDate = "2006"),
            Book(title = "The Grapes of Wrath", author = "John Steinbeck", genre = "Fiction", isbn = "456738201", publishedDate = "1939"),
            Book(title = "The Sun Also Rises", author = "Ernest Hemingway", genre = "Fiction", isbn = "384756291", publishedDate = "1926"),
            Book(title = "Gone with the Wind", author = "Margaret Mitchell", genre = "Historical", isbn = "987623451", publishedDate = "1936"),
            Book(title = "The Da Vinci Code", author = "Dan Brown", genre = "Mystery", isbn = "564738201", publishedDate = "2003"),
            Book(title = "Angels & Demons", author = "Dan Brown", genre = "Mystery", isbn = "564738202", publishedDate = "2000"),
            Book(title = "Inferno", author = "Dan Brown", genre = "Mystery", isbn = "564738203", publishedDate = "2013"),
            Book(title = "The Fault in Our Stars", author = "John Green", genre = "Romance", isbn = "382746192", publishedDate = "2012"),
            Book(title = "Looking for Alaska", author = "John Green", genre = "Romance", isbn = "382746193", publishedDate = "2005"),
        )
    }
}
