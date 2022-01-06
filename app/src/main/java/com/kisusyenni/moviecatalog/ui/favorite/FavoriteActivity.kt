package com.kisusyenni.moviecatalog.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kisusyenni.moviecatalog.R
import com.kisusyenni.moviecatalog.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        val favoritePagerAdapter = FavoritePageAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.fav_view_pager)
        viewPager.adapter = favoritePagerAdapter
        val tabs: TabLayout = findViewById(R.id.fav_tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
        title = resources.getString(R.string.favorite_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movies,
            R.string.tvShows
        )
    }
}