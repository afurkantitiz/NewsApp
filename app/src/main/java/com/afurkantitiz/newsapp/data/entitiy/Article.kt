package com.afurkantitiz.newsapp.data.entitiy
import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Article(
    val id: Int = 0,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: Date,
    val title: String,
    val url: String,
    val urlToImage: String
): Parcelable
