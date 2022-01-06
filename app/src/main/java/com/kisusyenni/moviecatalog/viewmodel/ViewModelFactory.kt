package com.kisusyenni.moviecatalog.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.di.Injection
import com.kisusyenni.moviecatalog.ui.detail.DetailViewModel
import com.kisusyenni.moviecatalog.ui.favorite.movie.FavoriteMovieViewModel
import com.kisusyenni.moviecatalog.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.kisusyenni.moviecatalog.ui.home.movie.MovieListViewModel
import com.kisusyenni.moviecatalog.ui.home.tvshow.TvShowListViewModel

class ViewModelFactory private constructor(private val movieCatalogRepository: MovieCatalogRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
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
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(movieCatalogRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) -> {
                FavoriteTvShowViewModel(movieCatalogRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}