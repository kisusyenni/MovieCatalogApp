package com.kisusyenni.moviecatalog.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kisusyenni.moviecatalog.R
import com.kisusyenni.moviecatalog.utils.MovieDummyData
import com.kisusyenni.moviecatalog.utils.TvShowDummyData
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
//    private val dummyMovies = MovieDummyData.generateMovie()
//    private val dummyTvShows = TvShowDummyData.generateTvShows()
//
//    @get:Rule
//    var activityRule = ActivityScenarioRule(HomeActivity::class.java)
//
//    @Test
//    fun loadMovies() {
//        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_movie_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
//    }
//
//    @Test
//    fun loadDetailMovie() {
//        onView(withId(R.id.rv_movie_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
//        onView(withId(R.id.text_title)).check(matches(withText(dummyMovies[0].title)))
//
//        onView(withId(R.id.overview_content)).check(matches(isDisplayed()))
//        onView(withId(R.id.overview_content)).check(matches(withText(dummyMovies[0].overview)))
//
//        onView(withId(R.id.quote)).check(matches(isDisplayed()))
//        onView(withId(R.id.quote)).check(matches(withText(dummyMovies[0].quote)))
//
//        onView(withId(R.id.director_content)).check(matches(isDisplayed()))
//        onView(withId(R.id.director_content)).check(matches(withText(dummyMovies[0].director)))
//
//        onView(withId(R.id.genres)).check(matches(isDisplayed()))
//        onView(withId(R.id.genres)).check(matches(withText("Genres: ${dummyMovies[0].genres}")))
//
//        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
//        onView(withId(R.id.release_year)).check(matches(withText(dummyMovies[0].releaseYear)))
//
//        onView(withId(R.id.duration_episodes)).check(matches(isDisplayed()))
//        onView(withId(R.id.duration_episodes)).check(matches(withText(dummyMovies[0].duration)))
//    }
//
//    @Test
//    fun loadDetailTvShow() {
//        onView(withText("Tv Shows")).perform(click())
//        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
//        onView(withId(R.id.text_title)).check(matches(withText(dummyTvShows[0].title)))
//
//        onView(withId(R.id.overview_content)).check(matches(isDisplayed()))
//        onView(withId(R.id.overview_content)).check(matches(withText(dummyTvShows[0].overview)))
//
//        onView(withId(R.id.quote)).check(matches(isDisplayed()))
//        onView(withId(R.id.quote)).check(matches(withText(dummyTvShows[0].quote)))
//
//        onView(withId(R.id.director_content)).check(matches(isDisplayed()))
//        onView(withId(R.id.director_content)).check(matches(withText(dummyTvShows[0].director)))
//
//        onView(withId(R.id.genres)).check(matches(isDisplayed()))
//        onView(withId(R.id.genres)).check(matches(withText("Genres: ${dummyTvShows[0].genres}")))
//
//        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
//        onView(withId(R.id.release_year)).check(matches(withText(dummyTvShows[0].releaseYear)))
//
//        onView(withId(R.id.duration_episodes)).check(matches(isDisplayed()))
//        onView(withId(R.id.duration_episodes)).check(matches(withText("${dummyTvShows[0].episodes} Episode")))
//    }
//
//    @Test
//    fun loadTvShows() {
//        onView(withText("Tv Shows")).perform(click())
//        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
//    }
}