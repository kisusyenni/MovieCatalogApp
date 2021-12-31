package com.kisusyenni.moviecatalog.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kisusyenni.moviecatalog.data.source.remote.RemoteDataSource
import com.kisusyenni.moviecatalog.utils.DataDummy
import com.kisusyenni.moviecatalog.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieCatalogRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val repository = FakeMovieCatalogRepository(remote)

    private val moviesResponse = DataDummy.generateRemoteMovies()
    private val movieId = moviesResponse[0].id.toString()
    private val movieDetailResponse = DataDummy.generateRemoteDetailMovie()

    private val tvShowsResponse = DataDummy.generateRemoteTvShows()
    private val tvShowId = tvShowsResponse[0].id.toString()
    private val tvShowDetailResponse = DataDummy.generateRemoteDetailTvShow()

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onMoviesLoaded(moviesResponse)
            null
        }.`when`(remote).getMovieList(any())

        val movies = LiveDataTestUtil.getValue(repository.getMovies())
        verify(remote).getMovieList(any())
        assertNotNull(movies)
        assertEquals(moviesResponse.size, movies.size)
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailMovieCallback).onDetailMovieLoaded(movieDetailResponse)
            null
        }.`when`(remote).getMovieDetail(any(), eq(movieId))

        val movieDetail = LiveDataTestUtil.getValue(repository.getDetailMovie(movieId))
        verify(remote).getMovieDetail(any(), eq(movieId))
        assertNotNull(movieDetail)
        assertEquals(movieDetail.id, movieDetail.id)
    }

    @Test
    fun getTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onTvShowsLoaded(tvShowsResponse)
            null
        }.`when`(remote).getTvShowList(any())

        val tvShows = LiveDataTestUtil.getValue(repository.getTvShows())
        verify(remote).getTvShowList(any())
        assertNotNull(tvShows)
        assertEquals(tvShowsResponse.size, tvShows.size)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailTvShowCallback).onDetailTvShowLoaded(tvShowDetailResponse)
            null
        }.`when`(remote).getTvShowDetail(any(), eq(tvShowId))

        val tvShowDetail = LiveDataTestUtil.getValue(repository.getDetailTvShow(tvShowId))
        verify(remote).getTvShowDetail(any(), eq(tvShowId))
        assertNotNull(tvShowDetail)
        assertEquals(tvShowDetailResponse.id, tvShowDetail.id)
    }
}