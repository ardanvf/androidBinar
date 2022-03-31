package com.example.recycleview_backup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_layout.view.*

class PhoneAdapter(val listPhone: ArrayList<Phone>): RecyclerView.Adapter<PhoneAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Phone>(){
        override fun areItemsTheSame(oldItem: Phone, newItem: Phone): Boolean {
            return oldItem._idPhone == newItem._idPhone
        }

        override fun areContentsTheSame(oldItem: Phone, newItem: Phone): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: ArrayList<Phone>) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]

        holder.itemView.textName.text = data._name
        holder.itemView.textYear.text = data._year
        holder.itemView.textBrand.text = data._brand
        data._image?.let { holder.itemView.imagePhone.setImageResource(it) }
    }

    override fun getItemCount(): Int = differ.currentList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}