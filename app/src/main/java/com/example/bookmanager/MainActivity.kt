package com.example.bookmanager

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components and set up listeners
        setupUI()
    }

    private fun setupUI() {
        val openBookListButton: Button = findViewById(R.id.openBookListButton)
        openBookListButton.setOnClickListener {
            startActivity(Intent(this, BookListActivity::class.java))
        }

        val clearDatabaseButton: Button = findViewById(R.id.clearDatabaseButton)
        clearDatabaseButton.setOnClickListener {
            bookViewModel.clearDatabase()
        }

        val addBookButton: Button = findViewById(R.id.addBookButton)
        addBookButton.setOnClickListener {
            showAddBookDialog()
        }

        val returnBooksButton: Button = findViewById(R.id.returnBooksButton)
        returnBooksButton.setOnClickListener {
            startActivity(Intent(this, ReturnBooksActivity::class.java))
        }

        // Set up button to add mock data to the database
        val addMockDataButton: Button = findViewById(R.id.addMockDataButton)
        addMockDataButton.setOnClickListener {
            addMockDataToDatabase()
        }
    }

    private fun showAddBookDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_book, null)
        val titleInput = dialogView.findViewById<EditText>(R.id.titleInput)
        val authorInput = dialogView.findViewById<EditText>(R.id.authorInput)
        val genreInput = dialogView.findViewById<EditText>(R.id.genreInput)
        val isbnInput = dialogView.findViewById<EditText>(R.id.isbnInput)
        val publishedDateInput = dialogView.findViewById<EditText>(R.id.publishedDateInput)

        AlertDialog.Builder(this)
            .setTitle("Add New Book")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val book = Book(
                    title = titleInput.text.toString(),
                    author = authorInput.text.toString(),
                    genre = genreInput.text.toString(),
                    isbn = isbnInput.text.toString(),
                    publishedDate = publishedDateInput.text.toString()
                )
                bookViewModel.insertBook(book)
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    private fun addMockDataToDatabase() {
        bookViewModel.insertMockData()
    }
}
