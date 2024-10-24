package com.example.bookmanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "author") var author: String,
    @ColumnInfo(name = "genre") var genre: String,
    @ColumnInfo(name = "isbn") var isbn: String,
    @ColumnInfo(name = "publishedDate") var publishedDate: String,
    @ColumnInfo(name = "isAvailable") var isAvailable: Boolean = true,
    @Ignore var isSelected: Boolean = false
) : Parcelable {
    constructor() : this(
        id = 0,
        title = "",
        author = "",
        genre = "",
        isbn = "",
        publishedDate = "",
        isAvailable = true
    )
}
