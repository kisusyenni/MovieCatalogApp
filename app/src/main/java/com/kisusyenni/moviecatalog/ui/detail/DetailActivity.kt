package com.kisusyenni.moviecatalog.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kisusyenni.moviecatalog.R
import com.kisusyenni.moviecatalog.data.MovieEntity
import com.kisusyenni.moviecatalog.data.TvShowEntity
import com.kisusyenni.moviecatalog.databinding.ActivityDetailBinding
import com.kisusyenni.moviecatalog.utils.MovieDummyData
import com.kisusyenni.moviecatalog.utils.TvShowDummyData

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_CATEGORY = "extra_category"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString(EXTRA_ID)
            val category = extras.getInt(EXTRA_CATEGORY)
            if (id != null) {
                if(category == 1) {
                    for (movie in MovieDummyData.generateMovie()) {
                        if (movie.movieId == id) {
                            populateMovie(movie)
                        }
                    }
                } else if (category == 2) {
                    for (tvShow in TvShowDummyData.generateTvShows()) {
                        if (tvShow.tvShowId == id) {
                            populateTvShow(tvShow)
                        }
                    }
                }

            }
        }
    }

    private fun populateDetail() {

    }

    private fun populateTvShow(tvShow: TvShowEntity) {

    }

    private fun populateMovie(movie: MovieEntity) {

    }
}