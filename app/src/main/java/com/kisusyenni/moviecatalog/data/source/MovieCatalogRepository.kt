package com.kisusyenni.moviecatalog.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kisusyenni.moviecatalog.data.NetworkBoundResource
import com.kisusyenni.moviecatalog.data.source.local.LocalDataSource
import com.kisusyenni.moviecatalog.data.source.local.entity.MovieEntity
import com.kisusyenni.moviecatalog.data.source.local.entity.TvShowEntity
import com.kisusyenni.moviecatalog.data.source.remote.ApiResponse
import com.kisusyenni.moviecatalog.data.source.remote.RemoteDataSource
import com.kisusyenni.moviecatalog.data.source.remote.api.POSTER_BASE_URL
import com.kisusyenni.moviecatalog.data.source.remote.response.MovieDetailResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.MovieResultsItem
import com.kisusyenni.moviecatalog.data.source.remote.response.TvShowDetailResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.TvShowResultsItem
import com.kisusyenni.moviecatalog.utils.AppExecutors
import com.kisusyenni.moviecatalog.vo.Resource

class MovieCatalogRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieCatalogDataSource {

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {

        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getMoviesList(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResultsItem>>> =
                remoteDataSource.getMovieList()

            override fun saveCallResult(data: List<MovieResultsItem>) {
                val list = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.overview,
                        "",
                        POSTER_BASE_URL + response.posterPath,
                        "",
                        response.voteAverage,
                        "",
                        response.releaseDate,
                        "",
                        false
                    )
                    list.add(movie)
                }
                localDataSource.insertMovies(list)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean = data != null

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovieDetail(movieId.toString())


            override fun saveCallResult(data: MovieDetailResponse) {
                val production = data.productionCompanies?.joinToString { it?.name!! }
                val genres = data.genres?.joinToString { it?.name!! }

                val detail = MovieEntity(
                    data.id,
                    data.title,
                    data.overview,
                    data.tagline,
                    POSTER_BASE_URL + data.posterPath,
                    production,
                    data.voteAverage,
                    genres,
                    data.releaseDate,
                    data.runtime.toString(),
                    false
                )
                localDataSource.updateMovie(detail, false)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, state)
        }
    }

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getTvShowsList(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResultsItem>>> =
                remoteDataSource.getTvShowList()

            override fun saveCallResult(data: List<TvShowResultsItem>) {
                val list = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.overview,
                        "",
                        POSTER_BASE_URL + response.posterPath,
                        "",
                        response.voteAverage,
                        "",
                        response.firstAirDate,
                        "",
                        false
                    )
                    list.add(tvShow)
                }
                localDataSource.insertTvShows(list)
            }

        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> = localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean = data != null

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> = remoteDataSource.getTvShowDetail(tvShowId.toString())

            override fun saveCallResult(data: TvShowDetailResponse) {
                val production = data.networks?.joinToString { it?.name!! }
                val genres = data.genres?.joinToString { it?.name!! }

                val detail = TvShowEntity(
                    data.id,
                    data.name,
                    data.overview,
                    data.tagline,
                    POSTER_BASE_URL + data.posterPath,
                    production,
                    data.voteAverage,
                    genres,
                    data.firstAirDate,
                    data.numberOfEpisodes.toString()
                )
                localDataSource.updateTvShow(detail, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShow, state)
        }
    }

    companion object {
        @Volatile
        private var instance: MovieCatalogRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieCatalogRepository =
            instance ?: synchronized(this) {
                instance ?: MovieCatalogRepository(remoteData, localDataSource, appExecutors)
            }
    }
}