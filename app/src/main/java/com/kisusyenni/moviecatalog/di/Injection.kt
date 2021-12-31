package com.kisusyenni.moviecatalog.di

import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): MovieCatalogRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return MovieCatalogRepository.getInstance(remoteDataSource)
    }
}