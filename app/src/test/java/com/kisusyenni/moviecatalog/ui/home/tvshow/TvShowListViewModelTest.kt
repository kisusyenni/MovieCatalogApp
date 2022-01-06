package com.kisusyenni.moviecatalog.ui.home.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kisusyenni.moviecatalog.data.source.MovieCatalogRepository
import com.kisusyenni.moviecatalog.data.source.local.entity.ListEntity
import com.kisusyenni.moviecatalog.utils.DataDummy
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowListViewModelTest {
    private lateinit var viewModel: TvShowListViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCatalogRepository

    @Mock
    private lateinit var observer: Observer<List<ListEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowListViewModel(repository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = DataDummy.getTvShows()
        val tvShows = MutableLiveData<List<ListEntity>>()
        tvShows.value = dummyTvShows
        `when`(repository.getTvShows()).thenReturn(tvShows)
        val tvShow = viewModel.getTvShows().value
        Mockito.verify(repository).getTvShows()
        TestCase.assertNotNull(tvShow)
        TestCase.assertEquals(3, tvShow?.size)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShows)
    }
}