package com.kisusyenni.moviecatalog.ui.movie

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieListViewModelTest {
    private lateinit var viewModel: MovieListViewModel
    @Before
    fun setUp() {
        viewModel = MovieListViewModel()
    }

    @Test
    fun getMovies() {
        val moviesEntities = viewModel.getMovies()
        assertNotNull(moviesEntities)
        assertEquals(11, moviesEntities.size)
    }
}