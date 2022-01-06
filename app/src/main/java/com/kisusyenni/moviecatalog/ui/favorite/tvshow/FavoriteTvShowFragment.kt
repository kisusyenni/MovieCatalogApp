package com.kisusyenni.moviecatalog.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kisusyenni.moviecatalog.databinding.FavoriteTvShowFragmentBinding
import com.kisusyenni.moviecatalog.ui.detail.DetailActivity
import com.kisusyenni.moviecatalog.viewmodel.ViewModelFactory

class FavoriteTvShowFragment : Fragment(), FavoriteTvShowAdapter.OnItemClickCallback {
    private var _fragmentFavTvShowBinding: FavoriteTvShowFragmentBinding? = null
    private val fragmentFavTvShowBinding get() = _fragmentFavTvShowBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentFavTvShowBinding =
            FavoriteTvShowFragmentBinding.inflate(layoutInflater, container, false)
        return fragmentFavTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = fragmentFavTvShowBinding.favTvShowsProgressBar
        val emptyText = fragmentFavTvShowBinding.tvEmptyFavTvshow
        progressBar.isVisible = true

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]
            val favTvShowAdapter = FavoriteTvShowAdapter()

            // observe tvShowList
            viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { tvShows ->

                progressBar.isVisible = false
                if (tvShows != null && tvShows.size > 0) {
                    favTvShowAdapter.submitList(tvShows)
                    favTvShowAdapter.setOnItemClickCallback(this)
                }

                emptyText.isVisible = tvShows.size <= 0

                with(fragmentFavTvShowBinding.rvFavTvShows) {
                    layoutManager =
                        if (resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
                            GridLayoutManager(context, 4)
                        } else {
                            GridLayoutManager(context, 3)
                        }
                    setHasFixedSize(true)
                    adapter = favTvShowAdapter
                }
            })
        }
    }
    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, "tvShow")
        context?.startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentFavTvShowBinding = null
    }
}