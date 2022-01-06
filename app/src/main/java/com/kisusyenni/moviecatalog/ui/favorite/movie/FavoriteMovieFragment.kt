package com.kisusyenni.moviecatalog.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kisusyenni.moviecatalog.databinding.FavoriteMovieFragmentBinding
import com.kisusyenni.moviecatalog.ui.detail.DetailActivity
import com.kisusyenni.moviecatalog.ui.home.movie.MovieListAdapter
import com.kisusyenni.moviecatalog.ui.home.movie.MovieListViewModel
import com.kisusyenni.moviecatalog.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment(), FavoriteMovieAdapter.OnItemClickCallback {

    private var _fragmentFavMovieBinding: FavoriteMovieFragmentBinding? = null
    private val fragmentFavMovieBinding get() = _fragmentFavMovieBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentFavMovieBinding =
            FavoriteMovieFragmentBinding.inflate(layoutInflater, container, false)
        return fragmentFavMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = fragmentFavMovieBinding.favMoviesProgressBar
        val emptyText = fragmentFavMovieBinding.tvEmptyFavMovie
        progressBar.isVisible = true
        emptyText.isVisible = false
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieListViewModel::class.java]
            val movieAdapter = MovieListAdapter()

            // observe movieList
            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                progressBar.isVisible = false
                if (movies != null) {
                    movieAdapter.submitList(movies.data)
                } else {
                    emptyText.isVisible = true
                }

                with(fragmentFavMovieBinding.rvFavMovie) {
                    layoutManager =
                        if (resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
                            GridLayoutManager(context, 4)
                        } else {
                            GridLayoutManager(context, 3)
                        }
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }

            })
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, "movie")
        context?.startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentFavMovieBinding = null
    }

}