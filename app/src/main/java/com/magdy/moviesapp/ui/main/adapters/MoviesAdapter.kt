package com.magdy.moviesapp.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.magdy.moviesapp.R
import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.core.utils.Constants.BASE_IMAGE_URL_API
import com.magdy.moviesapp.databinding.ItemMovieBinding

class MoviesAdapter : PagingDataAdapter<Movie, MoviesAdapter.MyViewHolder>(DiffUtilCallback()) {
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    class MyViewHolder(private val view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(item: Movie) = with(view) {
            title.text = item.title
            vote.text =
                String.format(
                    vote.context.getString(R.string.votes),
                    item.voteAverage,
                    item.voteCount
                )
            image.load(BASE_IMAGE_URL_API + item.posterPath)
            {
                crossfade(true)
                placeholder(R.mipmap.ic_launcher)
                transformations(CircleCropTransformation())
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title
        }

    }
}