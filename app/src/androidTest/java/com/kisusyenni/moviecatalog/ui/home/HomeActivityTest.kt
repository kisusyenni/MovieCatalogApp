package com.kisusyenni.moviecatalog.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
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
        onView(withId(R.id.rv_movie_list)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_episodes)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.genres)).check(matches(isDisplayed()))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_content)).check(matches(isDisplayed()))
        onView(withId(R.id.quote)).check(matches(isDisplayed()))
        onView(withId(R.id.production_content)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_episodes)).check(matches(isDisplayed()))
        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.genres)).check(matches(isDisplayed()))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_content)).check(matches(isDisplayed()))
        onView(withId(R.id.quote)).check(matches(isDisplayed()))
        onView(withId(R.id.production_content)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadFavoriteMovies() {
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.rv_fav_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
    }

    @Test
    fun loadDetailFavMovie() {
        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.rv_fav_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_episodes)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.genres)).check(matches(isDisplayed()))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_content)).check(matches(isDisplayed()))
        onView(withId(R.id.quote)).check(matches(isDisplayed()))
        onView(withId(R.id.production_content)).check(matches(isDisplayed()))

        onView(withId(R.id.fab)).perform(click())
    }

    @Test
    fun loadFavoriteTvShows() {
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_fav_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
    }

    @Test
    fun loadDetailFavTvShow() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.action_favorite)).perform(click())
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_fav_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_episodes)).check(matches(isDisplayed()))
        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.genres)).check(matches(isDisplayed()))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.overview_content)).check(matches(isDisplayed()))
        onView(withId(R.id.quote)).check(matches(isDisplayed()))
        onView(withId(R.id.production_content)).check(matches(isDisplayed()))
        onView(withId(R.id.fab)).perform(click())
    }

}