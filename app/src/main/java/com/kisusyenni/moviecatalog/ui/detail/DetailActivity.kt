package com.kisusyenni.moviecatalog.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kisusyenni.moviecatalog.R
import com.kisusyenni.moviecatalog.data.source.local.entity.DetailEntity
import com.kisusyenni.moviecatalog.databinding.ActivityDetailBinding
import com.kisusyenni.moviecatalog.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var categoryDetail: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString(EXTRA_ID)
            val category = extras.getString(EXTRA_CATEGORY)
            if (id != null && category != null) {
                categoryDetail = category
                viewModel.setDetail(id, category)
                viewModel.getDetail().observe(this, { data ->
                    populateDetail(data)
                })

            }
        }
    }

    private fun populateDetail(detail: DetailEntity) {
        title = detail.title
        activityDetailBinding.apply {
            textTitle.text = detail.title
            releaseYear.text = detail.releaseYear
            durationEpisodes.text = when(categoryDetail) {
                "movie" -> resources.getString(R.string.minutes, detail.durationEpisodes)
                "tvShow" -> resources.getString(R.string.episodes, detail.durationEpisodes)
                else -> ""
            }
            rbDetailRating.rating = detail.rating
            rbDetailRating.contentDescription = detail.rating.toString()
            genres.text = resources.getString(R.string.genres, detail.genres)
            quote.text = detail.quote
            overviewContent.text = detail.overview
            productionContent.text = detail.production
            imagePoster.contentDescription = detail.image
        }
        Glide.with(this@DetailActivity)
            .load(detail.image)
            .into(activityDetailBinding.imagePoster)
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_CATEGORY = "extra_category"
    }
}