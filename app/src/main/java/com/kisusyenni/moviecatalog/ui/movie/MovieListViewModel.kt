package com.kisusyenni.moviecatalog.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kisusyenni.moviecatalog.data.MovieEntity
import com.kisusyenni.moviecatalog.data.source.remote.api.ApiConfig
import com.kisusyenni.moviecatalog.data.source.remote.response.MovieResponse
import com.kisusyenni.moviecatalog.data.source.remote.response.MovieResultsItem
import com.kisusyenni.moviecatalog.utils.MovieDummyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel: ViewModel() {

    private val _movieList = MutableLiveData<List<MovieResultsItem>>()
    val movieList: LiveData<List<MovieResultsItem>> = _movieList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getMovieList()
    }

    private fun getMovieList() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getMovies(1)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _movieList.value = response.body()?.results
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getMovies(): List<MovieEntity> = MovieDummyData.generateMovie()

    companion object{
        private const val TAG = "MovieListViewModel"
    }
}