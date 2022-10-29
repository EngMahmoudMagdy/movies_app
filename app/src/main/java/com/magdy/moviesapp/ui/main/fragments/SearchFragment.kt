package com.magdy.moviesapp.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.magdy.moviesapp.databinding.FgNowPlayingBinding
import com.magdy.moviesapp.databinding.FgSearchBinding
import com.magdy.moviesapp.databinding.FgTopRatedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment:Fragment() {

    lateinit var binding: FgSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FgSearchBinding.inflate(inflater, container, false)
        return binding.root
    }
}