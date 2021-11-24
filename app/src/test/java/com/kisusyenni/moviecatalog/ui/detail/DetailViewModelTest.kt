package com.kisusyenni.moviecatalog.ui.detail

import com.kisusyenni.moviecatalog.utils.MovieDummyData
import com.kisusyenni.moviecatalog.utils.TvShowDummyData
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = MovieDummyData.generateMovie()[0]
    private val dummyTvShow = TvShowDummyData.generateTvShows()[0]
    private val movieId = dummyMovie.movieId
    private val tvShowId = dummyTvShow.tvShowId

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun getMovieDetail() {
        viewModel.setSelectedDetail(movieId)
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

    @Test
    fun getTvShowDetail() {
        viewModel.setSelectedDetail(tvShowId)
        val tvShowEntity = viewModel.getTvShowDetail()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dummyTvShow.title, tvShowEntity.title)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.quote, tvShowEntity.quote)
        assertEquals(dummyTvShow.image, tvShowEntity.image)
        assertEquals(dummyTvShow.director, tvShowEntity.director)
        assertEquals(dummyTvShow.rating, tvShowEntity.rating)
        assertEquals(dummyTvShow.genres, tvShowEntity.genres)
        assertEquals(dummyTvShow.releaseYear, tvShowEntity.releaseYear)
        assertEquals(dummyTvShow.episodes, tvShowEntity.episodes)
    }
}