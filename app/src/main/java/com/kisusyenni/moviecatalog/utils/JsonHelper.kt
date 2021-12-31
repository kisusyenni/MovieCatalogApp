package com.kisusyenni.moviecatalog.utils

import android.content.Context
import com.kisusyenni.moviecatalog.data.source.local.entity.ListEntity
import com.kisusyenni.moviecatalog.data.source.local.entity.MovieEntity
import com.kisusyenni.moviecatalog.data.source.local.entity.TvShowEntity
import com.kisusyenni.moviecatalog.data.source.remote.api.POSTER_BASE_URL
import com.kisusyenni.moviecatalog.data.source.remote.response.MovieDetailResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.MovieResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.TvShowDetailResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.TvShowResponse

class JsonHelper(private val context: Context) {

    fun loadMovieList(moviesData: MovieResponse): List<ListEntity> {
        val list = ArrayList<ListEntity>()
        for (data in moviesData.results!!) {

            val movie = ListEntity(
                data.id,
                data.title,
                data.releaseDate,
                calcRating(data.voteAverage),
                POSTER_BASE_URL+data.posterPath)
            list.add(movie)
        }
        return list
    }

    fun loadMovieDetail(movie: MovieDetailResponse): MovieEntity {
        val production = movie.productionCompanies?.joinToString { it?.name!! }
        val genres = movie.genres?.joinToString { it?.name!! }

        return MovieEntity(
            movie.id,
            movie.title,
            movie.overview,
            movie.tagline,
            POSTER_BASE_URL + movie.posterPath,
            production,
            calcRating(movie.voteAverage),
            genres,
            movie.releaseDate,
            movie.runtime.toString()
        )
    }

    fun loadTvShowList(tvShowsData: TvShowResponse): List<ListEntity> {
        val list = ArrayList<ListEntity>()
        for (data in tvShowsData.results!!) {

            val movie = ListEntity(
                data.id,
                data.name,
                data.firstAirDate,
                calcRating(data.voteAverage),
                POSTER_BASE_URL+data.posterPath)
            list.add(movie)
        }
        return list
    }

    fun loadTvShowDetail(tvShow: TvShowDetailResponse): TvShowEntity {
        val production = tvShow.networks?.joinToString { it?.name!! }
        val genres = tvShow.genres?.joinToString { it?.name!! }

        return TvShowEntity(
            tvShow.id,
            tvShow.name,
            tvShow.overview,
            tvShow.tagline,
            POSTER_BASE_URL + tvShow.posterPath,
            production,
            calcRating(tvShow.voteAverage),
            genres,
            tvShow.firstAirDate,
            tvShow.seasons?.size,
            tvShow.numberOfEpisodes
        )
    }

    private fun calcRating(rating: Double?): Float {
        return rating?.toFloat()!! /2
    }
}