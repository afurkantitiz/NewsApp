package com.afurkantitiz.newsapp.data.remote

import com.afurkantitiz.newsapp.data.entitiy.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("page=1&apiKey=4b1f9947220c420b92463819859a7e2e")
    suspend fun getNewsByQuery(@Query("everything") everything: String): Response<NewsResponse>
}