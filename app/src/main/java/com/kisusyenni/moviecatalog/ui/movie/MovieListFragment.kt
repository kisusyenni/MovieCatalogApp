package com.kisusyenni.moviecatalog.ui.movie

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisusyenni.moviecatalog.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {

    private lateinit var fragmentMovieListBinding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieListBinding =
            FragmentMovieListBinding.inflate(layoutInflater, container, false)
        return fragmentMovieListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieListViewModel::class.java]
            val movieAdapter = MovieListAdapter()

            viewModel.movieList.observe(viewLifecycleOwner, { movies ->

                movieAdapter.setMovies(movies)
                with(fragmentMovieListBinding.rvMovieList) {
                    if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        layoutManager = GridLayoutManager(context, 4)
                    } else {
                        layoutManager = GridLayoutManager(context, 3)
                    }
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }

            })
            viewModel.isLoading.observe(viewLifecycleOwner, {
                fragmentMovieListBinding.progressBar.visibility =
                    if (it) View.VISIBLE else View.GONE
            })

        }
    }
}