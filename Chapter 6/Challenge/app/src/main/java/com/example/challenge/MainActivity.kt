package com.example.challenge

import android.content.Intent
import android.content.pm.PackageManager
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge.Adapter.MainAdapter
import com.example.challenge.Api.Movie
import com.example.challenge.Api.MovieResponse
import com.example.challenge.Helper.Constant
import com.example.challenge.Helper.PreferencesHelper
import com.example.challenge.Network.ApiInterface
import com.example.challenge.Network.ApiService
import com.example.challenge.ViewModel.MainActivityViewModel
import com.example.challenge.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sharedpref: PreferencesHelper

    private val requestReceiveSms = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        sharedpref = PreferencesHelper(this)

        supportActionBar?.hide()
        binding.textView2.text = sharedpref.getString(Constant.PREF_USERNAME)
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.setHasFixedSize(true)

        //Sms Receiver
//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), requestReceiveSms)
//        }


        CoroutineScope(Dispatchers.Default).launch {
            delay(3000)
            viewModel.getMovieData {
                binding.progressBar.visibility = View.GONE
                binding.recycleView.adapter = MainAdapter(it, object : MainAdapter.OnClickListener {
                    override fun onClickItem(movie: Movie) {
                        val intentS = Intent(this@MainActivity, DetailActivity::class.java)
                        val bundle = Bundle()
                        bundle.putString("tittle", movie.title)
                        bundle.putString("release", movie.release)
                        bundle.putString("overview", movie.overview)
                        bundle.putString("poster", movie.poster)
                        bundle.putString("vote", movie.vote)
                        bundle.putString("language", movie.language)
                        intentS.putExtras(bundle)
                        startActivity(intentS)
                    }
                })
            }
        }

        binding.imageButton.setOnClickListener {
            startActivity(Intent(this, UpdateActivity::class.java))
        }

    }

    override fun onStart(){
        super.onStart()
        if(sharedpref.getBool(Constant.PREF_IS_LOGIN) == false){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}
