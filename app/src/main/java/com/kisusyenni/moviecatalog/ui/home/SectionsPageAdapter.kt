package com.kisusyenni.moviecatalog.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kisusyenni.moviecatalog.ui.movie.MovieListFragment
import com.kisusyenni.moviecatalog.ui.tvshow.TvShowListFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieListFragment()
            1 -> fragment = TvShowListFragment()
        }
        return fragment as Fragment
    }

}