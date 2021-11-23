package com.kisusyenni.moviecatalog.ui.tvshow

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class TvShowListViewModelTest {
    private lateinit var viewModel: TvShowListViewModel
    @Before
    fun setUp() {
        viewModel = TvShowListViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShowsEntities = viewModel.getTvShows()
        TestCase.assertNotNull(tvShowsEntities)
        TestCase.assertEquals(11, tvShowsEntities.size)
    }
}