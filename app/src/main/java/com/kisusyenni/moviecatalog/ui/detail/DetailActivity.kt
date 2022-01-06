package com.kisusyenni.moviecatalog.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kisusyenni.moviecatalog.R
import com.kisusyenni.moviecatalog.data.source.local.entity.MovieEntity
import com.kisusyenni.moviecatalog.data.source.local.entity.TvShowEntity
import com.kisusyenni.moviecatalog.databinding.ActivityDetailBinding
import com.kisusyenni.moviecatalog.viewmodel.ViewModelFactory
import com.kisusyenni.moviecatalog.vo.Status

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private var _activityDetailBinding: ActivityDetailBinding? = null
    private val activityDetailBinding get() = _activityDetailBinding!!

    private lateinit var categoryDetail: String
    private lateinit var viewModel: DetailViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        progressBar = activityDetailBinding.progressBar
        activityDetailBinding.fab.setOnClickListener(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString(EXTRA_ID)
            val category = extras.getString(EXTRA_CATEGORY)
            if (id != null && category != null) {
                categoryDetail = category
                viewModel.setDetail(id, category)
                setupState()
                when(category.uppercase()) {
                    "MOVIE"-> {
                        viewModel.getDetailMovie().observe(this, { detail ->
                            when(detail.status) {
                                Status.LOADING -> progressBar.isVisible = true
                                Status.SUCCESS -> {
                                    progressBar.isVisible = false
                                    detail.data?.let { populateDetailMovie(it) }
                                }
                                Status.ERROR -> {
                                    progressBar.isVisible = false
                                    Toast.makeText(applicationContext, "Error occured", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                    }
                    "TVSHOW" -> {
                        viewModel.getDetailTvShow().observe(this, { detail ->
                            when(detail.status) {
                                Status.LOADING -> progressBar.isVisible = true
                                Status.SUCCESS -> {
                                    progressBar.isVisible = false
                                    detail.data?.let { populateDetailTvShow(it) }
                                }
                                Status.ERROR -> {
                                    progressBar.isVisible = false
                                    Toast.makeText(applicationContext, "Error occured", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                    }
                }
            }
        }
    }

    private fun populateDetailMovie(detail: MovieEntity) {
        title = detail.title
        activityDetailBinding.apply {
            textTitle.text = detail.title
            releaseYear.text = detail.releaseDate
            durationEpisodes.text = resources.getString(R.string.minutes, detail.runtime)
            rbDetailRating.rating = calcRating(detail.voteAverage)
            rbDetailRating.contentDescription = detail.voteAverage.toString()
            genres.text = resources.getString(R.string.genres, detail.genres)
            quote.text = detail.tagline
            overviewContent.text = detail.overview
            productionContent.text = detail.productionCompanies
            imagePoster.contentDescription = detail.posterPath
        }
        Glide.with(this@DetailActivity)
            .load(detail.posterPath)
            .into(activityDetailBinding.imagePoster)
    }

    private fun populateDetailTvShow(detail: TvShowEntity) {
        title = detail.name
        activityDetailBinding.apply {
            textTitle.text = detail.name
            releaseYear.text = detail.firstAirDate
            durationEpisodes.text = resources.getString(R.string.episodes, detail.numberOfEpisodes)
            rbDetailRating.rating = calcRating(detail.voteAverage)
            rbDetailRating.contentDescription = detail.voteAverage.toString()
            genres.text = resources.getString(R.string.genres, detail.genres)
            quote.text = detail.tagline
            overviewContent.text = detail.overview
            productionContent.text = detail.productionCompanies
            imagePoster.contentDescription = detail.posterPath
        }
        Glide.with(this@DetailActivity)
            .load(detail.posterPath)
            .into(activityDetailBinding.imagePoster)
    }

    private fun calcRating(rating: Double?): Float {
        return if(rating != null) {
            (rating/2).toFloat()
        } else 0F
    }

    private fun onFabClicked() {
        if (categoryDetail.uppercase() == "MOVIE") {
            viewModel.setFavoriteMovie()
        } else if (categoryDetail.uppercase() == "TVSHOW") {
            viewModel.setFavoriteTvShow()
        }
    }

    private fun setFabState(state: Boolean) {
        val fab = activityDetailBinding.fab
        if (state) {
            fab.setImageResource(R.drawable.ic_favorite)
        } else {
            fab.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun setupState() {
        if (categoryDetail.uppercase() == "MOVIE") {
            viewModel.getDetailMovie().observe(this, { movie ->
                when (movie.status) {
                    Status.LOADING -> progressBar.isVisible = true
                    Status.SUCCESS -> {
                        if (movie.data != null) {
                            progressBar.isVisible = false
                            val state = movie.data.isFavorite
                            setFabState(state)
                        }
                    }
                    Status.ERROR -> {
                        progressBar.isVisible = false
                        Toast.makeText(applicationContext, "Error occurred", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else if (categoryDetail.uppercase() == "TVSHOW") {
            viewModel.getDetailTvShow().observe(this, { tvShow ->
                when (tvShow.status) {
                    Status.LOADING -> progressBar.isVisible = true
                    Status.SUCCESS -> {
                        if (tvShow.data != null) {
                            progressBar.isVisible = false
                            val state = tvShow.data.isFavorite
                            setFabState(state)
                        }
                    }
                    Status.ERROR -> {
                        progressBar.isVisible = false
                        Toast.makeText(applicationContext, "Error occured", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab-> {
                onFabClicked()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityDetailBinding = null
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_CATEGORY = "extra_category"
    }
}