package com.magdy.moviesapp.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.magdy.moviesapp.databinding.FgNowPlayingBinding
import com.magdy.moviesapp.ui.main.adapters.MoviesAdapter
import com.magdy.moviesapp.ui.main.viewModels.NowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NowPlayingFragment : Fragment() {

    lateinit var binding: FgNowPlayingBinding
    lateinit var moviesAdapter: MoviesAdapter
    val mViewModel by viewModels<NowPlayingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FgNowPlayingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViews() = with(binding) {
        moviesAdapter = MoviesAdapter()
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
    }


    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            mViewModel.getListData().collectLatest {
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