package com.magdy.moviesapp.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.magdy.moviesapp.databinding.FgNowPlayingBinding
import com.magdy.moviesapp.databinding.FgTopRatedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatedFragment: Fragment() {

    lateinit var binding: FgTopRatedBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FgTopRatedBinding.inflate(inflater, container, false)
        return binding.root
    }
}