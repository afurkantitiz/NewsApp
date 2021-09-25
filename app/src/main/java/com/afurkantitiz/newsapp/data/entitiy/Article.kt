package com.afurkantitiz.newsapp.data.entitiy
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: Date,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
): Parcelable