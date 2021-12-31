package com.kisusyenni.moviecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.data.source.local.entity.DetailEntity

class DetailViewModel(private val repository: MovieCatalogRepository): ViewModel() {
    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tvShow"
    }

    private lateinit var detailData: LiveData<DetailEntity>

    fun setDetail(id: String, category: String) {
        when (category) {
            MOVIE -> {
                detailData = repository.getDetailMovie(id)
            }

            TV_SHOW -> {
                detailData = repository.getDetailTvShow(id)
            }
        }
    }

    fun getDetail() = detailData
}