package com.kisusyenni.moviecatalog.ui.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.data.source.local.entity.MovieEntity
import com.kisusyenni.moviecatalog.vo.Resource

class MovieListViewModel(private val repository: MovieCatalogRepository): ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = repository.getMovies()
}