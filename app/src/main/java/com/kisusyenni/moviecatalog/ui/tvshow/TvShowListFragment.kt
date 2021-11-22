package com.kisusyenni.moviecatalog.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisusyenni.moviecatalog.databinding.FragmentTvShowListBinding
import com.kisusyenni.moviecatalog.utils.TvShowDummyData

class TvShowListFragment: Fragment() {
    private lateinit var fragmentTvShowListBinding: FragmentTvShowListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowListBinding = FragmentTvShowListBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val tvShows = TvShowDummyData.generateTvShows()
            val tvShowAdapter = TvShowListAdapter()
            tvShowAdapter.setTvShows(tvShows)
            with(fragmentTvShowListBinding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}