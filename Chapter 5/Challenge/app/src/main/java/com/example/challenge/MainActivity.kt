package com.example.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge.Adapter.MainAdapter
import com.example.challenge.Api.MovieList
import com.example.challenge.Network.ApiClient
import com.example.challenge.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import com.example.challenge.Api.Result


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        fetchAllData()
    }


    private fun fetchAllData() {
        ApiClient.instance.getMovie().enqueue(object :
            retrofit2.Callback<List<Result>>{
            override fun onResponse(
                call: Call<List<Result>>,
                response: Response<List<Result>>
            ) {
                val body = response.body()
                val code = response.code()
                if (code == 200) {
                    showList(body)
                    binding.progressBar.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<Result>>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun showList(data: List<Result>?) {
        val adapter = MainAdapter(object : MainAdapter.OnClickListener {
            override fun onClickItem(data: Result) {
            }
        })
        adapter.submitData(data)
        binding.recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = adapter
    }

}
