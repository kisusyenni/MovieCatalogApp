package com.kisusyenni.moviecatalog.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.kisusyenni.moviecatalog.R
import com.kisusyenni.moviecatalog.utils.DataDummy
import com.kisusyenni.moviecatalog.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    private val dummyMovies = DataDummy.getMovies()
    private val dummyTvShows = DataDummy.getTvShows()
    private val dummyDetailMovie = DataDummy.getDetailMovie()
    private val dummyDetailTvShows = DataDummy.getDetailTvShow()

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyDetailMovie.title)))
        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.release_year)).check(matches(withText(dummyDetailMovie.releaseYear)))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyDetailTvShows.title)))

        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.release_year)).check(matches(withText(dummyDetailTvShows.releaseYear)))
    }

    @Test
    fun loadTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }
}