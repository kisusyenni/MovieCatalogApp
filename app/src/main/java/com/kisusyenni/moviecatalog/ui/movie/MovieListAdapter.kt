package com.kisusyenni.moviecatalog.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kisusyenni.moviecatalog.data.source.local.entity.ListEntity
import com.kisusyenni.moviecatalog.databinding.ItemsMovieBinding

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    private var listMovies = ArrayList<ListEntity>()

    fun setMovies(movies: List<ListEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
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

    inner class MovieViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ListEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemReleased.text = movie.releasedDate
                movie.rating?.let {
                    rbItemRating.rating = movie.rating
                }
                itemView.setOnClickListener {onItemClickCallback.onItemClicked(movie.Id.toString())}
                Glide.with(itemView.context)
                    .load(movie.image)
                    .into(imgPoster)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }
}