package com.kisusyenni.moviecatalog.di

import android.content.Context
import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.data.source.local.LocalDataSource
import com.kisusyenni.moviecatalog.data.source.local.room.MovieCatalogDatabase
import com.kisusyenni.moviecatalog.data.source.remote.RemoteDataSource
import com.kisusyenni.moviecatalog.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieCatalogRepository {

        val database = MovieCatalogDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()

        val localDataSource = LocalDataSource.getInstance(database.movieCatalogDao())
        val appExecutors = AppExecutors()

        return MovieCatalogRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}