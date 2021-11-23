package com.kisusyenni.moviecatalog.ui.detail

import com.kisusyenni.moviecatalog.utils.MovieDummyData
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = MovieDummyData.generateMovie()[0]
    private val movieId = dummyMovie.movieId

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedDetail(movieId)
    }

    @Test
    fun getMovieDetail() {
        viewModel.setSelectedDetail(dummyMovie.movieId)
        val movieEntity = viewModel.getMovieDetail()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.quote, movieEntity.quote)
        assertEquals(dummyMovie.image, movieEntity.image)
        assertEquals(dummyMovie.director, movieEntity.director)
        assertEquals(dummyMovie.rating, movieEntity.rating)
        assertEquals(dummyMovie.genres, movieEntity.genres)
        assertEquals(dummyMovie.releaseYear, movieEntity.releaseYear)
        assertEquals(dummyMovie.duration, movieEntity.duration)
    }
}