package com.kisusyenni.moviecatalog.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kisusyenni.moviecatalog.R
import com.kisusyenni.moviecatalog.ui.movie.MovieListFragment
import com.kisusyenni.moviecatalog.ui.tvshow.TvShowListFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tvShows)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieListFragment()
            1 -> TvShowListFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2

}