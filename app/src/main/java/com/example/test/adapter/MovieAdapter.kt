package com.example.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.databinding.MovieItemBinding
import com.example.test.models.MovieItem
import com.example.test.models.MovieResponse
import com.example.test.utils.Constants

class MovieAdapter: ListAdapter<MovieItem, MovieAdapter.MovieViewHolder>(Companion){


    class MovieViewHolder(var binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieItem) {
            binding.txtMovieTitle.text = data.title
            Glide.with(binding.root.context).load(Constants.img_link+data.poster_path).into(binding.movieImg)

        }

    }

    companion object : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}