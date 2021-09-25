package com.afurkantitiz.newsapp.di

import android.content.Context
import androidx.room.Room
import com.afurkantitiz.newsapp.data.local.ArticleDao
import com.afurkantitiz.newsapp.data.local.LocalDataSource
import com.afurkantitiz.newsapp.data.local.RoomDB
import com.afurkantitiz.newsapp.data.local.SharedPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(
    SingletonComponent::class
)
class DatabaseModule {

    @Provides
    fun sharedPrefManager(@ApplicationContext context: Context): SharedPrefManager {
        return SharedPrefManager(context)
    }

    @Provides
    fun localDataSource(
        sharedPrefManager: SharedPrefManager,
        articleDao: ArticleDao
    ): LocalDataSource {
        return LocalDataSource(sharedPrefManager, articleDao)
    }

    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): RoomDB {
        return Room
            .databaseBuilder(context, RoomDB::class.java, "LocalDb")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(roomDB: RoomDB): ArticleDao {
        return roomDB.articleDao()
    }
}