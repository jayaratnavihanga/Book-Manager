package com.example.bookmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookListActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var bookAdapter: BookAdapter
    private lateinit var checkoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        // Initialize UI components and setup observers
        initViews()
        observeBooks()
        setupCheckoutButton()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        checkoutButton = findViewById(R.id.checkoutButton)

        // Initialize the adapter and RecyclerView
        bookAdapter = BookAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = bookAdapter
    }

    private fun observeBooks() {
        // Observe books data from the ViewModel and update the adapter's list
        bookViewModel.allBooks.observe(this, Observer { books ->
            bookAdapter.submitList(books)
        })
    }

    private fun setupCheckoutButton() {
        // Set up the checkout button's click listener
        checkoutButton.setOnClickListener {
            startCheckoutActivity()
        }
    }

    private fun startCheckoutActivity() {
        // Filter selected and available books
        val selectedBooks = bookAdapter.currentList.filter { it.isSelected && it.isAvailable }

        // Check if any books are selected
        if (selectedBooks.isEmpty()) {
            Toast.makeText(this, "No books selected or available for checkout", Toast.LENGTH_SHORT).show()
        } else {
            // Start the CheckoutActivity with selected books
            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putParcelableArrayListExtra("selectedBooks", ArrayList(selectedBooks))
            startActivity(intent)
        }
    }
}
