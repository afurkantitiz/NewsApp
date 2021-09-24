package com.afurkantitiz.newsapp.data.entitiy

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)