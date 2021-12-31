package com.kisusyenni.moviecatalog.data.source.remote.api

import com.kisusyenni.moviecatalog.data.source.remote.response.MovieDetailResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.MovieResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.TvShowDetailResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("discover/movie")
    fun getMovies(): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movie_id: String
    ): Call<MovieDetailResponse>

    @GET("discover/tv")
    fun getTvShows(): Call<TvShowResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") tv_id: String
    ): Call<TvShowDetailResponse>
}