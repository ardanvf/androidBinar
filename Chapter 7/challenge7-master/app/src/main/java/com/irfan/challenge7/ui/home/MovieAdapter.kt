package com.irfan.challenge7.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.irfan.challenge7.databinding.ItemMovieBinding
import com.irfan.challenge7.domain.model.Movie
import com.irfan.challenge7.utils.loadPhotoUrl

class MovieAdapter(
    private var onDetailClick: (Movie) -> Unit
) : PagingDataAdapter<Movie, MovieAdapter.CarViewHolder>(MovieDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder =
        CarViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.binding.apply {
                photoImageView.loadPhotoUrl(item.backdropPathUrl())
                titleTextView.text = item.originalTitle
                root.setOnClickListener {
                    onDetailClick(item)
                }
            }
        }

    }

    class CarViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val MovieDiffCallBack = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}