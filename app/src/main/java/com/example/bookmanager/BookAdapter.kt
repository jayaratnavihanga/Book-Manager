package com.example.bookmanager

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(
    private val returnMode: Boolean = false // Flag to indicate if the adapter is used for returning books
) : ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        private val selectCheckBox: CheckBox = itemView.findViewById(R.id.selectCheckBox)

        fun bind(book: Book) {
            titleTextView.text = book.title
            authorTextView.text = "by ${book.author}"
            selectCheckBox.isChecked = book.isSelected

            // Adjust the appearance based on availability and return mode
            if (returnMode) {
                // In return mode, ensure that all books are enabled for selection
                selectCheckBox.isEnabled = true
                itemView.alpha = 1.0f // Make sure the item is not greyed out
                titleTextView.paintFlags = titleTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                authorTextView.paintFlags = authorTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                titleTextView.setTextColor(Color.BLACK)
                authorTextView.setTextColor(Color.BLACK)
            } else {
                // Normal mode behavior: Grey out unavailable books and disable their checkboxes
                selectCheckBox.isEnabled = book.isAvailable
                itemView.alpha = if (book.isAvailable) 1.0f else 0.5f
                titleTextView.setTextColor(if (book.isAvailable) Color.BLACK else Color.GRAY)
                authorTextView.setTextColor(if (book.isAvailable) Color.BLACK else Color.GRAY)
                if (!book.isAvailable) {
                    titleTextView.paintFlags = titleTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    authorTextView.paintFlags = authorTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    titleTextView.paintFlags = titleTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    authorTextView.paintFlags = authorTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }

            // Update the selection state when the checkbox is checked/unchecked
            selectCheckBox.setOnCheckedChangeListener { _, isChecked ->
                book.isSelected = isChecked
            }
        }
    }

    class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }
}
