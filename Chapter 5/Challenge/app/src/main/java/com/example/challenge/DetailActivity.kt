package com.example.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge.Data.User
import com.example.challenge.Data.UserDatabase
import com.example.challenge.databinding.ActivityDetailBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private lateinit var binding: ActivityDetailBinding
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}