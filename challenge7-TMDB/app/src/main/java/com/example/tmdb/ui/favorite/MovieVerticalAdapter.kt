package com.example.tmdb.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.example.tmdb.databinding.ListItemMovieVerticalBinding
import com.example.tmdb.ui.shared.MovieItemClick
import com.example.tmdb.ui.shared.model.MovieItemModel
import com.example.tmdb.ui.utils.NumberFormatter

class MovieVerticalAdapter(
    private val onItemClick: MovieItemClick
) : ListAdapter<MovieItemModel, MovieVerticalAdapter.ViewHolder>(
    MovieItemModel.DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItemMovieVerticalBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], onItemClick)
    }

    inner class ViewHolder(
        private val binding: ListItemMovieVerticalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieItemModel, onItemClick: MovieItemClick) = with(binding) {
            root.setOnClickListener { onItemClick(item) }
            ivPoster.load(item.posterPath) {
                crossfade(true)
                transformations(RoundedCornersTransformation(16f, 0f, 16f, 0f))
            }
            tvTitle.text = item.title
            tvReleaseDate.text = "Release Date ${item.releaseDate}"
            tvDesc.text = item.overview
            tvRating.text = item.voteAverage.toString()
            val votes = NumberFormatter.formatSuffix(item.voteCount)
            tvVoteCount.text = "($votes votes)"
        }
    }

}