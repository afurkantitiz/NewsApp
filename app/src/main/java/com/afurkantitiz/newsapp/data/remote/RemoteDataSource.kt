package com.afurkantitiz.newsapp.data.remote

import com.afurkantitiz.newsapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: APIService
) : BaseDataSource() {
    suspend fun getNewsByQuery(query: String, page: Int) = getResult {
        apiService.getNewsByQuery(query, page)
    }
}