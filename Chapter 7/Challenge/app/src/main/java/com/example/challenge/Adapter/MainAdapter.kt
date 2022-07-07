package com.example.challenge.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge.Api.Movie
import com.example.challenge.databinding.ItemContentawBinding

class MainAdapter(private val movies: List<Movie>, private val onItemClick: OnClickListener
) : RecyclerView.Adapter<MainAdapter.MovieViewHolder>(){

    inner class MovieViewHolder(private val binding:ItemContentawBinding)
        :RecyclerView.ViewHolder(binding.root){
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie : Movie){
            binding.textTittle.text = movie.title
            binding.textVote.text = movie.release
            binding.textOverview.text = movie.overview
            Glide.with(itemView)
                .load(IMAGE_BASE + movie.poster)
                .into(binding.imageView)
            binding.root.setOnClickListener{
                onItemClick.onClickItem(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return MovieViewHolder(ItemContentawBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))
    }

    interface OnClickListener {
        fun onClickItem(movie: Movie)
    }
}