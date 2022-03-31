package com.example.recycleview_backup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleview_backup.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listPhones = arrayListOf(
            Phone(1, "Realme", "2010", "Oppo", R.drawable.ic_launcher_foreground),
            Phone(2,"iPhone 20", "2021", "Apple", R.drawable.ic_launcher_background),
            Phone(3, "Galaxy A10", "2022", "Samsung", R.drawable.ic_launcher_background),
            Phone(4, "Mac", "2020", "Xiaomi", R.drawable.ic_launcher_foreground),
            Phone(5, "Hwawei", "2021", "Huawei", R.drawable.ic_launcher_foreground)
        )

        binding.recycleView.isNestedScrollingEnabled = false

        val adapter = PhoneAdapter(listPhones)
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false)
        recycleView.adapter = adapter

        val adapterDiff = PhoneAdapter(listPhones)
        binding.recycleView.adapter = adapterDiff
        adapterDiff.submitData(listPhones)

        binding.btnDiff.setOnClickListener{
            val list: MutableList<Phone> = listPhones.toMutableList()
            list[3] = Phone(2, "ROG Phone", "9999", "Asus", R.drawable.rog)
            adapterDiff.differ.submitList(list)
        }
    }
}