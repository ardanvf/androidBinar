package com.irfan.challenge7.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.irfan.challenge7.databinding.ItemMovieBinding
import com.irfan.challenge7.domain.model.Movie
import com.irfan.challenge7.utils.loadPhotoUrl

class FavoriteMovieAdapter(
    private var onDetailClick: (Movie) -> Unit
) : ListAdapter<Movie, FavoriteMovieAdapter.CarViewHolder>(MovieDiffCallBack()) {

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
}

class MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}