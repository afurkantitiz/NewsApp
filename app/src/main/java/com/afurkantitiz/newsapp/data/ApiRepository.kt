package com.afurkantitiz.newsapp.data

import com.afurkantitiz.newsapp.data.entitiy.Article
import com.afurkantitiz.newsapp.data.local.LocalDataSource
import com.afurkantitiz.newsapp.data.remote.RemoteDataSource
import com.afurkantitiz.newsapp.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
) {
    fun getNewsByQuery(query: String) = performNetworkOperation {
        remoteDataSource.getNewsByQuery(query)
    }

    fun getFavoriteNews() = localDataSource.getFavoriteNews()

    fun addFavorite(article: Article) = localDataSource.addFavorite(article)

    fun unFavorite(article: Article) = localDataSource.unFavorite(article)
}