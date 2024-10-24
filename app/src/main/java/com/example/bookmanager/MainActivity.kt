package com.example.bookmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookViewModel.insertMockData()  // Optionally insert mock data

        // Set up button to open BookListActivity
        val openBookListButton: Button = findViewById(R.id.openBookListButton)
        openBookListButton.setOnClickListener {
            val intent = Intent(this, BookListActivity::class.java)
            startActivity(intent)
        }

        // Set up button to clear the database
        val clearDatabaseButton: Button = findViewById(R.id.clearDatabaseButton)
        clearDatabaseButton.setOnClickListener {
            bookViewModel.clearDatabase()
        }
    }
}
