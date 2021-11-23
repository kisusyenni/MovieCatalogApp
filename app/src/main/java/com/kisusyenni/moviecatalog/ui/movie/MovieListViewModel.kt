package com.kisusyenni.moviecatalog.ui.movie

import androidx.lifecycle.ViewModel
import com.kisusyenni.moviecatalog.data.MovieEntity
import com.kisusyenni.moviecatalog.utils.MovieDummyData

class MovieListViewModel: ViewModel() {
    fun getMovies(): List<MovieEntity> = MovieDummyData.generateMovie()
}