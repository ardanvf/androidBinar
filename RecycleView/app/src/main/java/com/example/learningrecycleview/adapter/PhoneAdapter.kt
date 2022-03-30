package com.example.learningrecycleview.adapter
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.learningrecycleview.R
import com.example.learningrecycleview.data.Phone
import kotlinx.android.synthetic.main.card_layout.view.*

class PhoneAdapter(val listPhone: ArrayList<Phone>): RecyclerView.Adapter<PhoneAdapter.ViewHolder>() {

    //Class Holder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //Membuat Holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    //Jumlah list yang akan ditampilkan berdasarkan banyaknya data di (size)
    override fun getItemCount(): Int {
        return listPhone.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.textName.text = listPhone[position]._name
        holder.itemView.textYear.text= listPhone[position]._year
        holder.itemView.textBrand.text = listPhone[position]._brand
//        holder.itemView.imagePhone.setImageDrawable = listPhone[position]._image
    }
}