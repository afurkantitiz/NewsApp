package com.afurkantitiz.newsapp.data.local

import com.afurkantitiz.newsapp.data.entitiy.Article
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val sharedPrefManager: SharedPrefManager,
    private val articleDao: ArticleDao
) {
    fun getFavoriteNews(): List<Article>{
        return articleDao.getFavoriteNews()
    }

    fun addFavorite(article: Article) {
        articleDao.addFavorite(article)
    }

    fun unFavorite(article: Article) {
        articleDao.unFavorite(article)
    }
}
