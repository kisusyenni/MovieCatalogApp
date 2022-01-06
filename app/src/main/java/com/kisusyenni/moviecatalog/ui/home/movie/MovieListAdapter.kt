package com.kisusyenni.moviecatalog.ui.home.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kisusyenni.moviecatalog.data.source.local.entity.MovieEntity
import com.kisusyenni.moviecatalog.databinding.ItemsMovieBinding

class MovieListAdapter :
    PagedListAdapter<MovieEntity, MovieListAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)

        if (movie != null) {
            holder.bind(movie)
        }
    }

    inner class MovieViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemReleased.text = movie.releaseDate
                rbItemRating.rating = calcRating(movie.voteAverage)
                itemView.setOnClickListener { onItemClickCallback.onItemClicked(movie.id.toString()) }
                Glide.with(itemView.context)
                    .load(movie.posterPath)
                    .into(imgPoster)
            }
        }
    }

    private fun calcRating(rating: Double?): Float {
        return if(rating != null) {
            (rating/2).toFloat()
        } else 0F
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}