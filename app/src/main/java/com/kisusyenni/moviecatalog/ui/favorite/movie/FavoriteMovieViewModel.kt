package com.kisusyenni.moviecatalog.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val repository: MovieCatalogRepository) : ViewModel(){
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = repository.getFavoriteMovies()

    fun setFavMovie(movieEntity: MovieEntity) {
        val state = !movieEntity.isFavorite
        repository.setFavoriteMovie(movieEntity, state)
    }
}