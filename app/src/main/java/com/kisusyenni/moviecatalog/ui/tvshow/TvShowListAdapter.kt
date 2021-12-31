package com.kisusyenni.moviecatalog.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kisusyenni.moviecatalog.data.source.local.entity.ListEntity
import com.kisusyenni.moviecatalog.databinding.ItemsMovieBinding
import com.kisusyenni.moviecatalog.ui.detail.DetailActivity

class TvShowListAdapter: RecyclerView.Adapter<TvShowListAdapter.MovieViewHolder>() {

    private var listTvShows = ArrayList<ListEntity>()

    fun setTvShows(tvShows: List<ListEntity>?) {
        if (tvShows == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val tvShow = listTvShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShows.size

    class MovieViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: ListEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.title
                tvItemReleased.text = tvShow.releasedDate
                tvShow.rating?.let{
                    rbItemRating.rating = tvShow.rating
                }
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, tvShow.Id.toString())
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, "tvShow")
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tvShow.image)
                    .into(imgPoster)
            }
        }
    }
}