package com.kisusyenni.moviecatalog.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisusyenni.moviecatalog.databinding.FragmentMovieListBinding
import com.kisusyenni.moviecatalog.utils.MovieDummyData

class MovieListFragment : Fragment() {

    private lateinit var fragmentMovieListBinding: FragmentMovieListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieListBinding = FragmentMovieListBinding.inflate(layoutInflater, container, false)
        return fragmentMovieListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movies = MovieDummyData.generateMovie()
            val movieAdapter = MovieListAdapter()
            movieAdapter.setMovies(movies)
            with(fragmentMovieListBinding.rvMovieList) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}