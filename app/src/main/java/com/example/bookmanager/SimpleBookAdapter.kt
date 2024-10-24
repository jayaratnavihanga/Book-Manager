package com.example.bookmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SimpleBookAdapter(
    private val books: List<Book>
) : RecyclerView.Adapter<SimpleBookAdapter.SimpleBookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleBookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_simple_book, parent, false)
        return SimpleBookViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleBookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    class SimpleBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)

        fun bind(book: Book) {
            titleTextView.text = book.title
        }
    }
}
