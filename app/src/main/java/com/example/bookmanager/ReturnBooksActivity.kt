package com.example.bookmanager

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReturnBooksActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModels()
    private lateinit var returnBooksRecyclerView: RecyclerView
    private lateinit var returnButton: Button
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_return_books)

        returnBooksRecyclerView = findViewById(R.id.returnBooksRecyclerView)
        returnButton = findViewById(R.id.returnButton)

        // Pass returnMode = true to ensure books are not greyed out
        bookAdapter = BookAdapter(returnMode = true)
        returnBooksRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ReturnBooksActivity)
            adapter = bookAdapter
        }

        // Observe only checked-out books (isAvailable = false)
        bookViewModel.allBooks.observe(this, { books ->
            val checkedOutBooks = books.filter { !it.isAvailable }
            bookAdapter.submitList(checkedOutBooks)
        })

        returnButton.setOnClickListener {
            handleReturnBooks()
        }
    }

    private fun handleReturnBooks() {
        val selectedBooks = bookAdapter.currentList.filter { it.isSelected }

        if (selectedBooks.isEmpty()) {
            Toast.makeText(this, "No books selected for return", Toast.LENGTH_SHORT).show()
            return
        }

        selectedBooks.forEach { book ->
            book.isAvailable = true // Mark as available
            bookViewModel.updateBook(book) // Update in the database
        }

        Toast.makeText(this, "${selectedBooks.size} books returned", Toast.LENGTH_SHORT).show()
        finish() // Go back to the main screen
    }
}
