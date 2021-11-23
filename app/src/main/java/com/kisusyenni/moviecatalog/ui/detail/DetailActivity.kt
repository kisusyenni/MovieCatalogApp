package com.kisusyenni.moviecatalog.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kisusyenni.moviecatalog.R
import com.kisusyenni.moviecatalog.data.MovieEntity
import com.kisusyenni.moviecatalog.data.TvShowEntity
import com.kisusyenni.moviecatalog.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var activityDetailBinding: ActivityDetailBinding
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_CATEGORY = "extra_category"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString(EXTRA_ID)
            val category = extras.getInt(EXTRA_CATEGORY)
            if (id != null) {
                if(category == 1) {
                    val movie = viewModel.getMovieDetail()
                    populateMovie(movie)
                } else if (category == 2) {
                    val tvShow = viewModel.getTvShowDetail()
                    populateTvShow(tvShow)
                }

            }
        }
    }

    private fun populateTvShow(tvShow: TvShowEntity) {
        activityDetailBinding.textTitle.text = tvShow.title
        activityDetailBinding.releaseYear.text = tvShow.releaseYear
        activityDetailBinding.durationEpisodes.text = resources.getString(R.string.episodes, tvShow.episodes.toString())
        activityDetailBinding.rbItemRating.rating = tvShow.rating.toFloat()/20
        activityDetailBinding.genres.text = resources.getString(R.string.genres, tvShow.genres)
        activityDetailBinding.quote.text = tvShow.quote
        activityDetailBinding.overviewContent.text = tvShow.overview
        activityDetailBinding.directorContent.text = tvShow.director
        Glide.with(this@DetailActivity)
            .load(tvShow.image)
            .into(activityDetailBinding.imagePoster)
    }

    private fun populateMovie(movie: MovieEntity) {
        activityDetailBinding.textTitle.text = movie.title
        activityDetailBinding.releaseYear.text = movie.releaseYear
        activityDetailBinding.durationEpisodes.text = movie.duration
        activityDetailBinding.rbItemRating.rating = movie.rating.toFloat()/20
        activityDetailBinding.genres.text = resources.getString(R.string.genres, movie.genres)
        activityDetailBinding.quote.text = movie.quote
        activityDetailBinding.overviewContent.text = movie.overview
        activityDetailBinding.directorContent.text = movie.director
        Glide.with(this@DetailActivity)
            .load(movie.image)
            .into(activityDetailBinding.imagePoster)
    }
}