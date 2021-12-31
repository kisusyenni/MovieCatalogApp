package com.kisusyenni.moviecatalog.data.source.remote

import android.util.Log
import com.kisusyenni.moviecatalog.data.source.remote.api.ApiConfig
import com.kisusyenni.moviecatalog.data.source.remote.response.*
import com.kisusyenni.moviecatalog.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        private const val TAG = "RemoteDataSourcce"
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getMovieList(callback: LoadMoviesCallback){
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getMovies()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                callback.onMoviesLoaded(response.body()?.results)
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
    }


    fun getMovieDetail(callback: LoadDetailMovieCallback, movieId: String){
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getMovieDetail(movieId)
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                callback.onDetailMovieLoaded(response.body())
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvShowList(callback: LoadTvShowsCallback){
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getTvShows()
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                callback.onTvShowsLoaded(response.body()?.results)
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
    }


    fun getTvShowDetail(callback: LoadDetailTvShowCallback, tvId: String) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getTvShowDetail(tvId)
        client.enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(
                call: Call<TvShowDetailResponse>,
                response: Response<TvShowDetailResponse>
            ) {
                callback.onDetailTvShowLoaded(response.body())
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface LoadMoviesCallback {
        fun onMoviesLoaded(movies : List<MovieResultsItem>?)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieLoaded(movieDetail : MovieDetailResponse?)
    }

    interface LoadTvShowsCallback {
        fun onTvShowsLoaded(tvShows : List<TvShowResultsItem>?)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowLoaded(tvShowDetail: TvShowDetailResponse?)
    }

}