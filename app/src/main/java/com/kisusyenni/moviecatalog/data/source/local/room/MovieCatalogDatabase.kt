package com.kisusyenni.moviecatalog.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kisusyenni.moviecatalog.data.source.local.entity.MovieEntity
import com.kisusyenni.moviecatalog.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false)
abstract class MovieCatalogDatabase : RoomDatabase() {
    abstract fun movieCatalogDao(): MovieCatalogDao

    companion object {

        @Volatile
        private var INSTANCE: MovieCatalogDatabase? = null

        fun getInstance(context: Context): MovieCatalogDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieCatalogDatabase::class.java,
                    "movie_catalog.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}