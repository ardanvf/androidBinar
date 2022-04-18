package com.example.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.data.GetAllCarResponseItem
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.network.ApiClient
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //View Model

        fetchAllData()
        setupView()
    }

    private fun setupView() {
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        }
    }

    private fun fetchAllData() {
        ApiClient.instance.getAllCar().enqueue(object :
            retrofit2.Callback<List<GetAllCarResponseItem>> {
                override fun onResponse(
                    call: Call<List<GetAllCarResponseItem>>,
                    response: Response<List<GetAllCarResponseItem>>
                ) {
                    val body = response.body()
                    val code = response.code()
                    if (code ==200){
                        showList(body)
                        binding.progressBar1.visibility = View.GONE
                    } else {
                        binding.progressBar1.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<List<GetAllCarResponseItem>>, t: Throwable){
                    binding.progressBar1.visibility = View.GONE
                }
            })
    }

    private fun showList(data: List<GetAllCarResponseItem>? ){
        val adapter = MainAdapter(object : MainAdapter.OnClickListener {
            override fun onClickItem(data: GetAllCarResponseItem){
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("id", "${data.id}")
                startActivity(intent)
                Toast.makeText(this@MainActivity, "Item Clicker ${data.id}", Toast.LENGTH_SHORT).show()
            }
        })
        adapter.submitData(data)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
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
                    if(code == 200)  {
                        binding.progressBar1.visibility = View.GONE
                    } else {
                        binding.progressBar1.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<GetAllCarResponseItem>, t: Throwable) {
                    binding.progressBar1.visibility = View.GONE
                }
            })
    }
}
