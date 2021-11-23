package com.kisusyenni.moviecatalog.ui.detail

import androidx.lifecycle.ViewModel
import com.kisusyenni.moviecatalog.data.MovieEntity
import com.kisusyenni.moviecatalog.data.TvShowEntity
import com.kisusyenni.moviecatalog.utils.MovieDummyData
import com.kisusyenni.moviecatalog.utils.TvShowDummyData

class DetailViewModel: ViewModel() {
    private lateinit var detailId: String

    fun setSelectedDetail(detailId: String) {
        this.detailId = detailId
    }

    fun getMovieDetail(): MovieEntity {
        lateinit var detail: MovieEntity
        val movieEntities = MovieDummyData.generateMovie()
        for (movie in movieEntities) {
            if (movie.movieId == detailId) {
                detail = movie
            }
        }
        return detail
    }

    fun getTvShowDetail(): TvShowEntity {
        lateinit var detail: TvShowEntity
        val tvShowEntities = TvShowDummyData.generateTvShows()
        for (tvShow in tvShowEntities) {
            if (tvShow.tvShowId == detailId) {
                detail = tvShow
            }
        }
        return detail
    }

}