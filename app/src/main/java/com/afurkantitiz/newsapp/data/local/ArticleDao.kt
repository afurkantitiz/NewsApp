package com.afurkantitiz.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.afurkantitiz.newsapp.data.entitiy.ArticleRoom

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article ORDER BY id DESC")
    fun getFavoriteNews(): List<ArticleRoom>

    @Insert
    fun addFavorite(article: ArticleRoom?)

    @Delete
    fun unFavorite(article: ArticleRoom?)
}