package com.kisusyenni.moviecatalog.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kisusyenni.moviecatalog.R
import com.kisusyenni.moviecatalog.utils.DataDummy
import com.kisusyenni.moviecatalog.utils.EspressoIdlingResource
import com.kisusyenni.moviecatalog.utils.MovieDummyData
import com.kisusyenni.moviecatalog.utils.TvShowDummyData
import org.junit.After
import org.junit.Before
import org.junit.Rule
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
//
//        onView(withId(R.id.overview_content)).check(matches(isDisplayed()))
//        onView(withId(R.id.overview_content)).check(matches(withText(dummyDetailMovie.overview)))
//
//        onView(withId(R.id.quote)).check(matches(isDisplayed()))
//        onView(withId(R.id.quote)).check(matches(withText(dummyDetailMovie.quote)))
//
//        onView(withId(R.id.director_content)).check(matches(isDisplayed()))
//        onView(withId(R.id.director_content)).check(matches(withText(dummyDetailMovie.production)))
//
//        onView(withId(R.id.genres)).check(matches(isDisplayed()))
//        onView(withId(R.id.genres)).check(matches(withText("Genres: ${dummyDetailMovie.genres}")))

        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.release_year)).check(matches(withText(dummyDetailMovie.releaseYear)))
//
//        onView(withId(R.id.duration_episodes)).check(matches(isDisplayed()))
//        onView(withId(R.id.duration_episodes)).check(matches(withText(dummyDetailMovie.durationSeasons)))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyDetailTvShows.title)))

//        onView(withId(R.id.overview_content)).check(matches(isDisplayed()))
//        onView(withId(R.id.overview_content)).check(matches(withText(dummyDetailTvShows.overview)))

//        onView(withId(R.id.quote)).check(matches(isDisplayed()))
//        onView(withId(R.id.quote)).check(matches(withText(dummyDetailTvShows.quote)))

//        onView(withId(R.id.director_content)).check(matches(isDisplayed()))
//        onView(withId(R.id.director_content)).check(matches(withText(dummyDetailTvShows.production)))
//
//        onView(withId(R.id.genres)).check(matches(isDisplayed()))
//        onView(withId(R.id.genres)).check(matches(withText("Genres: ${dummyDetailTvShows.genres}")))

        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.release_year)).check(matches(withText(dummyDetailTvShows.releaseYear)))

//        onView(withId(R.id.duration_episodes)).check(matches(isDisplayed()))
//        onView(withId(R.id.duration_episodes)).check(matches(withText("${dummyDetailTvShows.durationSeasons} Episode")))
    }

    @Test
    fun loadTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }
}