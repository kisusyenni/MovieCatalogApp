package com.kisusyenni.moviecatalog.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.data.source.local.entity.DetailEntity
import com.kisusyenni.moviecatalog.ui.detail.DetailViewModel.Companion.MOVIE
import com.kisusyenni.moviecatalog.ui.detail.DetailViewModel.Companion.TV_SHOW
import com.kisusyenni.moviecatalog.utils.DataDummy
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.getDetailMovie()
    private val dummyTvShow = DataDummy.getDetailTvShow()
    private val movieId = dummyMovie.id.toString()
    private val tvShowId = dummyTvShow.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCatalogRepository

    @Mock
    private lateinit var observer: Observer<DetailEntity>

    @Before
    fun setUpMovie() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getMovieDetail() {
        val movie = MutableLiveData<DetailEntity>()
        movie.value = dummyMovie

        `when`(repository.getDetailMovie(movieId)).thenReturn(movie)
        viewModel.setDetail(movieId, MOVIE)
        val detailMovie = viewModel.getDetail().value as DetailEntity
        verify(repository).getDetailMovie(movieId)

        assertNotNull(detailMovie)
        assertEquals(dummyMovie.id, detailMovie.id)
        assertEquals(dummyMovie.title, detailMovie.title)
        assertEquals(dummyMovie.overview, detailMovie.overview)
        assertEquals(dummyMovie.quote, detailMovie.quote)
        assertEquals(dummyMovie.image, detailMovie.image)
        assertEquals(dummyMovie.production, detailMovie.production)
        assertEquals(dummyMovie.rating, detailMovie.rating)
        assertEquals(dummyMovie.genres, detailMovie.genres)
        assertEquals(dummyMovie.releaseYear, detailMovie.releaseYear)
        assertEquals(dummyMovie.durationSeasons, detailMovie.durationSeasons)

        viewModel.getDetail().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Before
    fun setupTvShow() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getTvShowDetail() {
        val tvShow = MutableLiveData<DetailEntity>()
        tvShow.value = dummyTvShow

        `when`(repository.getDetailTvShow(tvShowId)).thenReturn(tvShow)
        viewModel.setDetail(tvShowId, TV_SHOW)
        val detailTvShow = viewModel.getDetail().value as DetailEntity
        verify(repository).getDetailTvShow(tvShowId)

        assertNotNull(detailTvShow)
        assertEquals(dummyTvShow.id, detailTvShow.id)
        assertEquals(dummyTvShow.title, detailTvShow.title)
        assertEquals(dummyTvShow.overview, detailTvShow.overview)
        assertEquals(dummyTvShow.quote, detailTvShow.quote)
        assertEquals(dummyTvShow.image, detailTvShow.image)
        assertEquals(dummyTvShow.production, detailTvShow.production)
        assertEquals(dummyTvShow.rating, detailTvShow.rating)
        assertEquals(dummyTvShow.genres, detailTvShow.genres)
        assertEquals(dummyTvShow.releaseYear, detailTvShow.releaseYear)
        assertEquals(dummyTvShow.durationSeasons, detailTvShow.durationSeasons)

        viewModel.getDetail().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}