package com.kisusyenni.moviecatalog.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kisusyenni.moviecatalog.data.source.local.entity.ListEntity
import com.kisusyenni.moviecatalog.databinding.ItemsMovieBinding
import com.kisusyenni.moviecatalog.ui.detail.DetailActivity

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<ListEntity>()

    fun setMovies(movies: List<ListEntity>?) {
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
        fun bind(movie: ListEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemReleased.text = movie.releasedDate
                movie.rating?.let {
                    rbItemRating.rating = movie.rating
                }
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, movie.Id.toString())
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, "movie")
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(movie.image)
                    .into(imgPoster)
            }
        }
    }
}