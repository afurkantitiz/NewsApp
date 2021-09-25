package com.afurkantitiz.newsapp.data.remote

import com.afurkantitiz.newsapp.data.entitiy.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("everything?page=1&apiKey=564a8a088b1f4412ad7c61d426ff9bfa")
    suspend fun getNewsByQuery(@Query("q") q: String): Response<NewsResponse>
}