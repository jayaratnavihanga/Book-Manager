package com.example.bookmanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "genre") val genre: String,
    @ColumnInfo(name = "isbn") val isbn: String,
    @ColumnInfo(name = "publishedDate") val publishedDate: String,
    @ColumnInfo(name = "isAvailable") val isAvailable: Boolean = true
)
