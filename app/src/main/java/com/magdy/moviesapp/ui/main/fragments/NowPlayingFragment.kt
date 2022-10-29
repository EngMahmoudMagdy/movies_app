package com.magdy.moviesapp.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.magdy.moviesapp.R
import com.magdy.moviesapp.core.services.NetworkResult
import com.magdy.moviesapp.core.utils.ToastUtils.showToast
import com.magdy.moviesapp.databinding.FgNowPlayingBinding
import com.magdy.moviesapp.ui.main.adapters.MoviesAdapter
import com.magdy.moviesapp.ui.main.viewModels.NowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NowPlayingFragment : Fragment() {

    lateinit var binding: FgNowPlayingBinding
    lateinit var adapter: MoviesAdapter
    val mViewModel by viewModels<NowPlayingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FgNowPlayingBinding.inflate(inflater, container, false)
        binding.viewModel = mViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchData()
    }

    private fun initViews() = with(binding){
        adapter = MoviesAdapter()
        recycler.adapter = adapter
    }

    private fun fetchData() {
        mViewModel.fetchNowPlayingResponse()
        mViewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.swipeRefresh.isRefreshing = false
                    showToast(R.string.success)
                }
                is NetworkResult.Error -> {
                    binding.swipeRefresh.isRefreshing = false

                }
                is NetworkResult.Loading -> {
                    binding.swipeRefresh.isRefreshing = true
                }
            }
        }
    }
}