package com.kisusyenni.moviecatalog.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.kisusyenni.moviecatalog.data.source.local.entity.MovieEntity
import com.kisusyenni.moviecatalog.data.source.local.entity.TvShowEntity
import com.kisusyenni.moviecatalog.data.source.local.room.MovieCatalogDao

class LocalDataSource(private val mMovieCatalogDao: MovieCatalogDao) {

    // movies local data source

    fun getMoviesList(): DataSource.Factory<Int, MovieEntity> = mMovieCatalogDao.getMovies()

    fun getMovieById(id: Int): LiveData<MovieEntity> = mMovieCatalogDao.getMovieById(id)

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mMovieCatalogDao.getFavoriteMovies()

    fun insertMovies(movies: List<MovieEntity>) = mMovieCatalogDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        mMovieCatalogDao.updateMovie(movie)
    }

    fun updateMovie(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        mMovieCatalogDao.updateMovie(movie)
    }

    // tv shows local data source

    fun getTvShowsList(): DataSource.Factory<Int, TvShowEntity> = mMovieCatalogDao.getTvShows()

    fun getTvShowById(id: Int): LiveData<TvShowEntity> = mMovieCatalogDao.getTvShowById(id)

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> = mMovieCatalogDao.getFavoriteTvShow()

    fun insertTvShows(tvShows: List<TvShowEntity>) = mMovieCatalogDao.insertTvShows(tvShows)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        tvShow.isFavorite = state
        mMovieCatalogDao.updateTvShow(tvShow)
    }

    fun updateTvShow(tvShow: TvShowEntity, state: Boolean) {
        tvShow.isFavorite = state
        mMovieCatalogDao.updateTvShow(tvShow)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieCatalogDao: MovieCatalogDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieCatalogDao)
    }
}