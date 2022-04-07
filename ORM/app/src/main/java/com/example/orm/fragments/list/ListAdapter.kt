package com.example.orm.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.orm.R
import com.example.orm.model.User
import com.example.orm.databinding.CustomLayoutBinding


private var _binding: CustomLayoutBinding? = null
private val binding get() = _binding!!

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_layout, parent, false))
    }

    override fun getItemCount(): Int{
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        binding.textID.text = currentItem.id.toString()
        binding.textName.text = currentItem.name
        binding.textEmail.text = currentItem.email
        binding.textAge.text = currentItem.age.toString()
//        holder.itemView.textID.text = currentItem.id.toString()
//        holder.itemView.textName.text = currentItem.name
//        holder.itemView.textEmail.text = currentItem.email
//        holder.itemView.textAge.text = currentItem.age.toString()

        binding.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragmet(currentItem)
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}