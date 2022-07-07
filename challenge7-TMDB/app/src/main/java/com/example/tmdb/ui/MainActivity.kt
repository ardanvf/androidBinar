package com.example.tmdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tmdb.databinding.ActivityMainBinding
import com.example.tmdb.ui.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}