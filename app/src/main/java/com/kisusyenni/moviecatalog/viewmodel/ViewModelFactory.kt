package com.kisusyenni.moviecatalog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.di.Injection
import com.kisusyenni.moviecatalog.ui.detail.DetailViewModel
import com.kisusyenni.moviecatalog.ui.movie.MovieListViewModel
import com.kisusyenni.moviecatalog.ui.tvshow.TvShowListViewModel

class ViewModelFactory private constructor(private val movieCatalogRepository: MovieCatalogRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository()).apply { instance = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieListViewModel::class.java) -> {
                MovieListViewModel(movieCatalogRepository) as T
            }
            modelClass.isAssignableFrom(TvShowListViewModel::class.java) -> {
                TvShowListViewModel(movieCatalogRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieCatalogRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}