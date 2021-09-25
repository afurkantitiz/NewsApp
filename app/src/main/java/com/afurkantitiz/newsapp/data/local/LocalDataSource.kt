package com.afurkantitiz.newsapp.data.local

import com.afurkantitiz.newsapp.data.entitiy.ArticleRoom
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val sharedPrefManager: SharedPrefManager,
    private val articleDao: ArticleDao
) {
    fun getFavoriteNews(): List<ArticleRoom>{
        return articleDao.getFavoriteNews()
    }

    fun addFavorite(article: ArticleRoom) {
        articleDao.addFavorite(article)
    }

    fun unFavorite(article: ArticleRoom) {
        articleDao.unFavorite(article)
    }
}
