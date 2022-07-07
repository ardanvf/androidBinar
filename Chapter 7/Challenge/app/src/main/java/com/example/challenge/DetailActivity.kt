package com.example.challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.challenge.Api.MovieResponse
import com.example.challenge.Data.User
import com.example.challenge.Data.UserDatabase
import com.example.challenge.Network.ApiInterface
import com.example.challenge.Network.ApiService
import com.example.challenge.databinding.ActivityDetailBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityDetailBinding
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        val bundle = intent.extras

        binding.textTittle.text = bundle!!.getString("tittle", "")
        binding.textRating.text = bundle!!.getString("vote","")
        binding.textOverview.text = bundle!!.getString("overview", "")
        binding.textVote.text = bundle!!.getString("language", "")
        Glide.with(this)
            .load(IMAGE_BASE + bundle!!.getString("poster", ""))
            .into(binding.imageView3)
//        getMovieDetail(414906)
    }


//    private fun getMovieDetail(movieId: Int){
//        val apiService = ApiService.getInstance().create(ApiInterface::class.java)
//        apiService.getMovieId(movieId).enqueue(object : Callback<MovieResponse>{
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                binding.textTittle.text = response.body().toString()
//            }
//        })
//    }
}