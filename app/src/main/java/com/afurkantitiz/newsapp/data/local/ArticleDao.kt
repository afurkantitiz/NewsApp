package com.afurkantitiz.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.afurkantitiz.newsapp.data.entitiy.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article ORDER BY id DESC")
    fun getFavoriteNews(): List<Article>

    @Insert
    fun addFavorite(article: Article?)

    @Delete
    fun unFavorite(article: Article?)
}