package com.example.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.data.GetAllCarResponseItem
import com.example.retrofit.databinding.ActivityDetailBinding
import com.example.retrofit.databinding.ItemContentBinding
import com.example.retrofit.network.ApiClient
import retrofit2.Call
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getId = intent.getStringExtra("id")
        val getName = intent.getStringExtra("name")
        val getCategory = intent.getStringExtra("category")
        val getPrice = intent.getStringExtra("price")


        if (getName != null) {
            binding.textDtNameCar.text = getName
            binding.textDtCategory.text = getCategory
            binding.textDtPrice.text = getPrice.toString()
            binding.progressBar2.visibility = View.GONE
        }

        getCarDetail(getId.toString().toInt())


    }
    private fun getCarDetail(id: Int) {
        ApiClient.instance.getCarById(id)
            .enqueue(object : retrofit2.Callback<GetAllCarResponseItem> {
                override fun onResponse(
                    call: Call<GetAllCarResponseItem>,
                    response: Response<GetAllCarResponseItem>
                ) {
                    val body = response.body()
                    val code = response.code()

                    if(code == 201)  {
                    } else {
                    }
                }

                override fun onFailure(call: Call<GetAllCarResponseItem>, t: Throwable) {
                    Toast.makeText(this@DetailActivity, "Gagal mendapatkan detail", Toast.LENGTH_SHORT).show()
                }
            })
    }
}