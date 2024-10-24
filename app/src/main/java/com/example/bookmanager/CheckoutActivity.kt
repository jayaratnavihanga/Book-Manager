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

class CheckoutActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModels()
    private lateinit var checkoutRecyclerView: RecyclerView
    private lateinit var addMoreBooksButton: Button
    private lateinit var removeAndCheckoutButton: Button
    private lateinit var bookAdapter: BookAdapter
    private var selectedBooks: ArrayList<Book> = arrayListOf()

    private val ADD_BOOKS_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        initViews()
        selectedBooks = loadSelectedBooks()
        setupRecyclerView()
        setupButtonListeners()
    }

    private fun initViews() {
        checkoutRecyclerView = findViewById(R.id.checkoutRecyclerView)
        addMoreBooksButton = findViewById(R.id.addMoreBooksButton)
        removeAndCheckoutButton = findViewById(R.id.removeAndCheckoutButton)
    }

    private fun loadSelectedBooks(): ArrayList<Book> {
        return intent.getParcelableArrayListExtra<Book>("selectedBooks") ?: arrayListOf()
    }

    private fun setupRecyclerView() {
        bookAdapter = BookAdapter()
        checkoutRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CheckoutActivity)
            adapter = bookAdapter
            bookAdapter.submitList(selectedBooks)
        }
    }

    private fun setupButtonListeners() {
        addMoreBooksButton.setOnClickListener {
            val intent = Intent(this, AddBooksActivity::class.java)
            startActivityForResult(intent, ADD_BOOKS_REQUEST_CODE)
        }

        removeAndCheckoutButton.setOnClickListener {
            handleCheckout()
        }
    }

    private fun handleCheckout() {
        if (selectedBooks.isEmpty()) {
            Toast.makeText(this, "No books selected for checkout", Toast.LENGTH_SHORT).show()
            return
        }


        selectedBooks.forEach { book ->
            book.isAvailable = false // Mark as unavailable
            bookViewModel.updateBook(book) // Update the database
        }

        val intent = Intent(this, CheckoutSuccessfulActivity::class.java)
        intent.putParcelableArrayListExtra("checkedOutBooks", selectedBooks)
        startActivity(intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_BOOKS_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val addedBooks = data?.getParcelableArrayListExtra<Book>("addedBooks") ?: arrayListOf()

            if (addedBooks.isNotEmpty()) {
                // Add the new books to the existing list and create a new list instance to trigger update
                selectedBooks.addAll(addedBooks)
                bookAdapter.submitList(ArrayList(selectedBooks)) // Ensure a new instance is passed to submitList
            }
        }
    }

}
