package com.example.learningrecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningrecycleview.adapter.PhoneAdapter
import com.example.learningrecycleview.data.Phone
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_layout.*

class MainActivity : AppCompatActivity() {

//    private var layoutManager: RecyclerView.LayoutManager? = null
//    private var adapter: RecyclerView.Adapter<RecyclerView> = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listPhones = arrayListOf(
            Phone(1, "Realme", "2010", "Oppo", R.drawable.ic_launcher_background),
            Phone(2,"iPhone 20", "2021", "Apple", R.drawable.ic_launcher_background),
            Phone(3, "Galaxy A10", "2022", "Samsung", R.drawable.ic_launcher_background)
        )

        val adapter = PhoneAdapter(listPhones)

        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false)
        recycleView.adapter = adapter

    }
}