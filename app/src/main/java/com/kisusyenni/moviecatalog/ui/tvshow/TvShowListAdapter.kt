package com.kisusyenni.moviecatalog.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kisusyenni.moviecatalog.data.source.local.entity.ListEntity
import com.kisusyenni.moviecatalog.databinding.ItemsMovieBinding

class TvShowListAdapter: RecyclerView.Adapter<TvShowListAdapter.MovieViewHolder>() {

    private var listTvShows = ArrayList<ListEntity>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setTvShows(tvShows: List<ListEntity>?) {
        if (tvShows == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShows)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
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

    inner class MovieViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: ListEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.title
                tvItemReleased.text = tvShow.releasedDate
                tvShow.rating?.let{
                    rbItemRating.rating = tvShow.rating
                }
                Glide.with(itemView.context)
                    .load(tvShow.image)
                    .into(imgPoster)
                itemView.setOnClickListener {onItemClickCallback.onItemClicked(tvShow.Id.toString())}
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }
}