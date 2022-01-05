package com.kisusyenni.moviecatalog.ui.movie

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kisusyenni.moviecatalog.databinding.FragmentMovieListBinding
import com.kisusyenni.moviecatalog.ui.detail.DetailActivity
import com.kisusyenni.moviecatalog.viewmodel.ViewModelFactory
import com.kisusyenni.moviecatalog.vo.Status

class MovieListFragment : Fragment(), MovieListAdapter.OnItemClickCallback {

    private var _fragmentMovieListBinding: FragmentMovieListBinding? = null
    private val fragmentMovieListBinding get() = _fragmentMovieListBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentMovieListBinding =
            FragmentMovieListBinding.inflate(layoutInflater, container, false)
        return fragmentMovieListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = fragmentMovieListBinding.moviesProgressBar
        progressBar.isVisible = true
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieListViewModel::class.java]
            val movieAdapter = MovieListAdapter()

            // observe movieList
            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                if(movies != null) {
                    when(movies.status) {
                        Status.LOADING -> progressBar.isVisible = true
                        Status.SUCCESS -> {
                            progressBar.isVisible = false
                            movieAdapter.submitList(movies.data)
                            movieAdapter.setOnItemClickCallback(this)
                        }
                        Status.ERROR -> {
                            progressBar.isVisible = false
                            Toast.makeText(context, "Error occured", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                with(fragmentMovieListBinding.rvMovieList) {
                    layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
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
        _fragmentMovieListBinding = null
    }
}