package com.afurkantitiz.newsapp.data

import com.afurkantitiz.newsapp.data.remote.RemoteDataSource
import com.afurkantitiz.newsapp.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource
) {
    fun getNewsByQuery(query: String) = performNetworkOperation {
        remoteDataSource.getNewsByQuery(query)
    }
}