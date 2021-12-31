package com.kisusyenni.moviecatalog.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kisusyenni.moviecatalog.databinding.FragmentTvShowListBinding
import com.kisusyenni.moviecatalog.ui.detail.DetailActivity
import com.kisusyenni.moviecatalog.viewmodel.ViewModelFactory

class TvShowListFragment: Fragment(), TvShowListAdapter.OnItemClickCallback {
    private var _fragmentTvShowListBinding: FragmentTvShowListBinding? = null
    private val fragmentTvShowListBinding get() = _fragmentTvShowListBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _fragmentTvShowListBinding = FragmentTvShowListBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = fragmentTvShowListBinding.tvShowsProgressBar
        progressBar.isVisible = true
        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[TvShowListViewModel::class.java]
            val tvShowAdapter = TvShowListAdapter()

            // observe tvShowList
            viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->

                progressBar.isVisible = false
                tvShowAdapter.setTvShows(tvShows)
                tvShowAdapter.setOnItemClickCallback(this)
                with(fragmentTvShowListBinding.rvTvShows) {
                    layoutManager = if (resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
                        GridLayoutManager(context, 4)
                    } else {
                        GridLayoutManager(context, 3)
                    }
                    setHasFixedSize(true)
                    adapter = tvShowAdapter
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
        _fragmentTvShowListBinding = null
    }
}