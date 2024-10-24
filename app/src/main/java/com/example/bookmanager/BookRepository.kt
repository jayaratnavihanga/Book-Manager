package com.example.bookmanager

import androidx.lifecycle.LiveData

class BookRepository(private val bookDao: BookDao) {

    val allBooks: LiveData<List<Book>> = bookDao.getAllBooks()

    suspend fun insert(book: Book) = bookDao.insert(book)

    suspend fun insertAll(books: List<Book>) = bookDao.insertAll(books)

    suspend fun update(book: Book) = bookDao.update(book)

    suspend fun delete(book: Book) = bookDao.delete(book)
}
