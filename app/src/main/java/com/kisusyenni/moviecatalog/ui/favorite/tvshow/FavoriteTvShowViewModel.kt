package com.kisusyenni.moviecatalog.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel(private val repository: MovieCatalogRepository) : ViewModel() {
    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> = repository.getFavoriteTvShows()

    fun setFavTvShow(tvShowEntity: TvShowEntity) {
        val state = !tvShowEntity.isFavorite
        repository.setFavoriteTvShow(tvShowEntity, state)
    }
}