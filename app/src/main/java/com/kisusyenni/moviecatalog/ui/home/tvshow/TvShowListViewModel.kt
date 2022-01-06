package com.kisusyenni.moviecatalog.ui.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.data.source.local.entity.TvShowEntity
import com.kisusyenni.moviecatalog.vo.Resource

class TvShowListViewModel(private val repository: MovieCatalogRepository): ViewModel() {
    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = repository.getTvShows()
}