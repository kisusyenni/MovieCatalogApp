package com.kisusyenni.moviecatalog.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kisusyenni.moviecatalog.data.source.local.entity.TvShowEntity
import com.kisusyenni.moviecatalog.databinding.ItemsMovieBinding

class TvShowListAdapter :
    PagedListAdapter<TvShowEntity, TvShowListAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

//    private var listTvShows = ArrayList<TvShowEntity>()

    private lateinit var onItemClickCallback: OnItemClickCallback

//    fun setTvShows(tvShows: List<T>?) {
//        if (tvShows == null) return
//        this.listTvShows.clear()
//        this.listTvShows.addAll(tvShows)
//    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)

        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

//    override fun getItemCount(): Int = listTvShows.size

    inner class TvShowViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.name
                tvItemReleased.text = tvShow.firstAirDate
                rbItemRating.rating = calcRating(tvShow.voteAverage)
                Glide.with(itemView.context)
                    .load(tvShow.posterPath)
                    .into(imgPoster)
                itemView.setOnClickListener { onItemClickCallback.onItemClicked(tvShow.id.toString()) }
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}