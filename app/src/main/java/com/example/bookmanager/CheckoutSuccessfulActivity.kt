package com.example.bookmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CheckoutSuccessfulActivity : AppCompatActivity() {

    private lateinit var checkedOutBooksRecyclerView: RecyclerView
    private lateinit var goToHomeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_successful)

        // Get the list of checked-out books from the intent
        val checkedOutBooks = intent.getParcelableArrayListExtra<Book>("checkedOutBooks") ?: arrayListOf()

        // Set up the RecyclerView to display book titles
        checkedOutBooksRecyclerView = findViewById(R.id.checkedOutBooksRecyclerView)
        val bookAdapter = SimpleBookAdapter(checkedOutBooks)
        checkedOutBooksRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CheckoutSuccessfulActivity)
            adapter = bookAdapter
        }

        // Set up the "Go to Home Screen" button
        goToHomeButton = findViewById(R.id.goToHomeButton)
        goToHomeButton.setOnClickListener {
            // Finish this activity and go back to the home screen (MainActivity)
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
