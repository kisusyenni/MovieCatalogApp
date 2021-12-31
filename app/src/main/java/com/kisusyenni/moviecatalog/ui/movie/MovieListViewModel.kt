package com.kisusyenni.moviecatalog.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.data.source.local.entity.ListEntity

class MovieListViewModel(private val repository: MovieCatalogRepository): ViewModel() {
    fun getMovies(): LiveData<List<ListEntity>> = repository.getMovies()
}