package com.kisusyenni.moviecatalog.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kisusyenni.moviecatalog.data.source.local.entity.DetailEntity
import com.kisusyenni.moviecatalog.data.source.local.entity.ListEntity
import com.kisusyenni.moviecatalog.data.source.remote.RemoteDataSource
import com.kisusyenni.moviecatalog.data.source.remote.api.POSTER_BASE_URL
import com.kisusyenni.moviecatalog.data.source.remote.response.MovieDetailResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.MovieResultsItem
import com.kisusyenni.moviecatalog.data.source.remote.response.TvShowDetailResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.TvShowResultsItem

class MovieCatalogRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieCatalogDataSource{

    override fun getMovies(): LiveData<List<ListEntity>> {
        val movieResults = MutableLiveData<List<ListEntity>>()

        remoteDataSource.getMovieList(object: RemoteDataSource.LoadMoviesCallback{
            override fun onMoviesLoaded(movies: List<MovieResultsItem>?) {
                val list = ArrayList<ListEntity>()
                if(movies != null) {
                    for (data in movies) {
                        val movie = ListEntity(
                            data.id,
                            data.title,
                            data.releaseDate,
                            calcRating(data.voteAverage),
                            POSTER_BASE_URL +data.posterPath)
                        list.add(movie)
                    }
                    movieResults.postValue(list)
                }
            }
        })
        return movieResults
    }

    override fun getDetailMovie(movieId: String): LiveData<DetailEntity> {
        val movieDetailResults = MutableLiveData<DetailEntity>()

        remoteDataSource.getMovieDetail(object: RemoteDataSource.LoadDetailMovieCallback{
            override fun onDetailMovieLoaded(movieDetail: MovieDetailResponse?) {
                if(movieDetail!=null) {
                    val production = movieDetail.productionCompanies?.joinToString { it?.name!! }
                    val genres = movieDetail.genres?.joinToString { it?.name!! }

                    val detail = DetailEntity(
                        movieDetail.id,
                        movieDetail.title,
                        movieDetail.overview,
                        movieDetail.tagline,
                        POSTER_BASE_URL + movieDetail.posterPath,
                        production,
                        calcRating(movieDetail.voteAverage),
                        genres,
                        movieDetail.releaseDate,
                        movieDetail.runtime.toString()
                    )

                    movieDetailResults.postValue(detail)

                }
            }

        }, movieId)
        return movieDetailResults
    }

    override fun getTvShows(): LiveData<List<ListEntity>> {
        val tvShowResults = MutableLiveData<List<ListEntity>>()

        remoteDataSource.getTvShowList(object: RemoteDataSource.LoadTvShowsCallback{
            override fun onTvShowsLoaded(tvShows: List<TvShowResultsItem>?) {
                val list = ArrayList<ListEntity>()
                if (tvShows != null) {
                    for (data in tvShows) {
                        val tvShow = ListEntity(
                            data.id,
                            data.name,
                            data.firstAirDate,
                            calcRating(data.voteAverage),
                            POSTER_BASE_URL+data.posterPath)
                        list.add(tvShow)
                    }

                }
                tvShowResults.postValue(list)
            }
        })
        return tvShowResults
    }

    override fun getDetailTvShow(tvShowId: String): LiveData<DetailEntity> {
        val tvShowDetailResults = MutableLiveData<DetailEntity>()

        remoteDataSource.getTvShowDetail(object: RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTvShowLoaded(tvShowDetail: TvShowDetailResponse?) {
                val production = tvShowDetail?.networks?.joinToString { it?.name!! }
                val genres = tvShowDetail?.genres?.joinToString { it?.name!! }

                val detail = DetailEntity(
                    tvShowDetail?.id,
                    tvShowDetail?.name,
                    tvShowDetail?.overview,
                    tvShowDetail?.tagline,
                    POSTER_BASE_URL + tvShowDetail?.posterPath,
                    production,
                    calcRating(tvShowDetail?.voteAverage),
                    genres,
                    tvShowDetail?.firstAirDate,
                    tvShowDetail?.numberOfEpisodes.toString()
                )

                tvShowDetailResults.postValue(detail)
            }

        }, tvShowId)
        return tvShowDetailResults
    }


    private fun calcRating(rating: Double?): Float {
        return if(rating != null) {
            (rating/2).toFloat()
        } else 0F
    }

    companion object {
        @Volatile
        private var instance: MovieCatalogRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MovieCatalogRepository =
            instance ?: synchronized(this) {
                instance ?: MovieCatalogRepository(remoteData).apply { instance = this }
            }
    }
}