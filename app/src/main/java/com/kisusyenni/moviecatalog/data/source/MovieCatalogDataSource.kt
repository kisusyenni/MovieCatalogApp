package com.kisusyenni.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.kisusyenni.moviecatalog.data.source.local.entity.DetailEntity
import com.kisusyenni.moviecatalog.data.source.local.entity.ListEntity

interface MovieCatalogDataSource {
    fun getMovies(): LiveData<List<ListEntity>>
    fun getDetailMovie(movieId: String): LiveData<DetailEntity>
    fun getTvShows(): LiveData<List<ListEntity>>
    fun getDetailTvShow(tvShowId: String): LiveData<DetailEntity>
}