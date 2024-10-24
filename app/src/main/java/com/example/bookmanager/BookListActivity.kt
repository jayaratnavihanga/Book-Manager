package com.example.bookmanager

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookListActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        recyclerView = findViewById(R.id.recyclerView)
        bookAdapter = BookAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@BookListActivity)
            adapter = bookAdapter
        }

        // Observe books data from ViewModel
        bookViewModel.allBooks.observe(this, Observer { books ->
            bookAdapter.submitList(books)
        })
    }
}
