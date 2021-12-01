package com.kisusyenni.moviecatalog.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kisusyenni.moviecatalog.R
import com.kisusyenni.moviecatalog.data.MovieEntity
import com.kisusyenni.moviecatalog.data.source.remote.api.POSTER_BASE_URL
import com.kisusyenni.moviecatalog.data.source.remote.response.MovieResultsItem
import com.kisusyenni.moviecatalog.databinding.ItemsMovieBinding
import com.kisusyenni.moviecatalog.ui.detail.DetailActivity

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<MovieResultsItem>()

    fun setMovies(movies: List<MovieResultsItem>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResultsItem) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemReleased.text = movie.releaseDate
                movie.voteAverage?.let {
                    rbItemRating.rating = (it/2).toFloat()
                }
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, movie.id)
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, 1)
                    itemView.context.startActivity(intent)
                }
                val posterUrl = POSTER_BASE_URL + movie.posterPath
                Glide.with(itemView.context)
                    .load(posterUrl)
                    .into(imgPoster)
            }
        }
    }
}