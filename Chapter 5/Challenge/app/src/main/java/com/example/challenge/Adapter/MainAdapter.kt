package com.example.challenge.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.challenge.Api.Result
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge.Api.MovieList
import com.example.challenge.databinding.ItemContentawBinding

class MainAdapter(private val onItemClick: OnClickListener):
    RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    private val diffCallback = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean = oldItem.hashCode() == newItem.hashCode()
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: List<Result>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemContentawBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let { holder.bind(data) }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemContentawBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: Result){
            binding.apply {
                textTittle.text = data.title
                textVote.text = data.voteAverage.toString()
                textOverview.text = data.overview
                root.setOnClickListener {
                    onItemClick.onClickItem(data)
                }
            }
        }
    }

    interface OnClickListener{
        fun onClickItem(data: Result)
    }
}