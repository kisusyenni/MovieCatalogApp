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

        onView(withId(R.id.duration_episodes)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_episodes)).check(matches(withText("${dummyDetailMovie.durationEpisodes} Minutes")))

        onView(withId(R.id.rb_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_detail_rating)).check(matches(withContentDescription(dummyDetailMovie.rating.toString())))

        onView(withId(R.id.genres)).check(matches(isDisplayed()))
        onView(withId(R.id.genres)).check(matches(withText("Genres: ${dummyDetailMovie.genres}")))

        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.image_poster)).check(matches(withContentDescription(dummyDetailMovie.image)))

        onView(withId(R.id.overview_content)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_content)).check(matches(withText(dummyDetailMovie.overview)))

        onView(withId(R.id.quote)).check(matches(isDisplayed()))
        onView(withId(R.id.quote)).check(matches(withText(dummyDetailMovie.quote)))

        onView(withId(R.id.production_content)).check(matches(isDisplayed()))
        onView(withId(R.id.production_content)).check(matches(withText(dummyDetailMovie.production)))


    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyDetailTvShows.title)))

        onView(withId(R.id.duration_episodes)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_episodes)).check(matches(withText("${dummyDetailTvShows.durationEpisodes} Episode")))

        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.release_year)).check(matches(withText(dummyDetailTvShows.releaseYear)))

        onView(withId(R.id.rb_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_detail_rating)).check(matches(withContentDescription(dummyDetailTvShows.rating.toString())))

        onView(withId(R.id.genres)).check(matches(isDisplayed()))
        onView(withId(R.id.genres)).check(matches(withText("Genres: ${dummyDetailTvShows.genres}")))

        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.image_poster)).check(matches(withContentDescription(dummyDetailTvShows.image)))

        onView(withId(R.id.overview_content)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_content)).check(matches(withText(dummyDetailTvShows.overview)))

        onView(withId(R.id.quote)).check(matches(isDisplayed()))
        onView(withId(R.id.quote)).check(matches(withText(dummyDetailTvShows.quote)))

        onView(withId(R.id.production_content)).check(matches(isDisplayed()))
        onView(withId(R.id.production_content)).check(matches(withText(dummyDetailTvShows.production)))

    }

    @Test
    fun loadTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }
}