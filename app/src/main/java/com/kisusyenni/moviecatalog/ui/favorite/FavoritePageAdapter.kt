package com.kisusyenni.moviecatalog.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kisusyenni.moviecatalog.ui.favorite.movie.FavoriteMovieFragment
import com.kisusyenni.moviecatalog.ui.favorite.tvshow.FavoriteTvShowFragment
import com.kisusyenni.moviecatalog.ui.home.movie.MovieListFragment
import com.kisusyenni.moviecatalog.ui.home.tvshow.TvShowListFragment

class FavoritePageAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FavoriteMovieFragment()
            1 -> fragment = FavoriteTvShowFragment()
        }
        return fragment as Fragment
    }
}