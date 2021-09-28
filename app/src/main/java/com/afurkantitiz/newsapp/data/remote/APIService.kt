package com.afurkantitiz.newsapp.data.remote

import com.afurkantitiz.newsapp.data.entitiy.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("everything?apiKey=022b316c1b3a455ab28a69c540b73f1e")
    suspend fun getNewsByQuery(
        @Query("q") q: String,
        @Query("page") page: Int
    ): Response<NewsResponse>
}