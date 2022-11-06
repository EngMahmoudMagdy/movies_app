package com.magdy.moviesapp.ui.main.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.core.utils.Constants
import com.magdy.moviesapp.databinding.FgSearchBinding
import com.magdy.moviesapp.ui.details.MovieDetailsActivity
import com.magdy.moviesapp.ui.main.adapters.MoviesAdapter
import com.magdy.moviesapp.ui.main.viewModels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    lateinit var moviesAdapter: MoviesAdapter
    private val mViewModel by viewModels<SearchViewModel>()
    lateinit var binding: FgSearchBinding

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                moviesAdapter.refresh()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FgSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViews() = with(binding) {
        moviesAdapter = MoviesAdapter{
            openMoviesDetailsActivityWithResult(it)
        }
        recycler.run {
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.HORIZONTAL
                )
            )
            this.adapter = moviesAdapter
        }
        swipeRefresh.setOnRefreshListener {
            moviesAdapter.refresh()
        }
        searchText.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                lifecycleScope.launch {
                    mViewModel.getListData(textView.text.toString())
                }
            }
            true
        }
    }


    private fun openMoviesDetailsActivityWithResult(movie: Movie) {
        resultLauncher.launch(
            Intent(
                requireContext(),
                MovieDetailsActivity::class.java
            ).apply {
                putExtra(
                    Constants.MOVIE, movie
                )
            }
        )
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            mViewModel.postList.collectLatest {
                moviesAdapter.submitData(it)
            }
        }
        lifecycleScope.launch {
            moviesAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }
    }
}