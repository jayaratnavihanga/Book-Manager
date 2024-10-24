package com.example.bookmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AddBooksActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModels()
    private lateinit var addBooksRecyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_books)

        addBooksRecyclerView = findViewById(R.id.addBooksRecyclerView)
        addButton = findViewById(R.id.addButton)

        // Use the book adapter with default settings (returnMode = false)
        bookAdapter = BookAdapter()
        addBooksRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@AddBooksActivity)
            adapter = bookAdapter
        }

        // Observe only available books (isAvailable = true)
        bookViewModel.allBooks.observe(this, { books ->
            val availableBooks = books.filter { it.isAvailable }
            bookAdapter.submitList(availableBooks)
        })

        addButton.setOnClickListener {
            handleAddBooks()
        }
    }

    private fun handleAddBooks() {
        val selectedBooks = bookAdapter.currentList.filter { it.isSelected }

        if (selectedBooks.isEmpty()) {
            Toast.makeText(this, "No books selected to add", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent().apply {
            putParcelableArrayListExtra("addedBooks", ArrayList(selectedBooks))
        }
        setResult(Activity.RESULT_OK, intent)
        finish() // Return to CheckoutActivity with the selected books
    }

}
