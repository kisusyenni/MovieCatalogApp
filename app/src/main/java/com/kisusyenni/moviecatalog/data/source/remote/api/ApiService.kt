package com.kisusyenni.moviecatalog.data.source.remote.api

import com.kisusyenni.moviecatalog.data.source.remote.response.MovieDetailResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.MovieResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.TvShowDetailResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("movie/popular")
    fun getMovies(
        @Query("page") page:Int
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movie_id: String
    ): Call<MovieDetailResponse>

    @GET("tv/popular")
    fun getTvShows(
        @Query("page") page:Int
    ): Call<TvShowResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") tv_id: String
    ): Call<TvShowDetailResponse>
}