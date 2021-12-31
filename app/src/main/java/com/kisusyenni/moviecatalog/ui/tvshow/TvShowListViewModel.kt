package com.kisusyenni.moviecatalog.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.data.source.local.entity.ListEntity

class TvShowListViewModel(private val repository: MovieCatalogRepository): ViewModel() {
    fun getTvShows(): LiveData<List<ListEntity>> = repository.getTvShows()
}