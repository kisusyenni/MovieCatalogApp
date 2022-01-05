package com.kisusyenni.moviecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.data.source.local.entity.MovieEntity
import com.kisusyenni.moviecatalog.data.source.local.entity.TvShowEntity
import com.kisusyenni.moviecatalog.vo.Resource

class DetailViewModel(private val repository: MovieCatalogRepository) : ViewModel() {

    private lateinit var detailMovieData: LiveData<Resource<MovieEntity>>
    private lateinit var detailTvShowData: LiveData<Resource<TvShowEntity>>

    fun setDetail(id: String, category: String) {
        when (category) {
            MOVIE -> {
                detailMovieData = repository.getDetailMovie(id.toInt())
            }

            TV_SHOW -> {
                detailTvShowData = repository.getDetailTvShow(id.toInt())
            }
        }
    }

    fun setFavoriteMovie() {
        val resource = detailMovieData.value
        if (resource?.data != null) {
            val isFavorite = !resource.data.isFavorite
            repository.setFavoriteMovie(resource.data, isFavorite)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTvShowData.value
        if (resource?.data != null) {
            val isFavorite = !resource.data.isFavorite
            repository.setFavoriteTvShow(resource.data, isFavorite)
        }
    }

    fun getDetailMovie() = detailMovieData
    fun getDetailTvShow() = detailTvShowData

    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tvShow"
    }
}