package com.example.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge.databinding.ActivityUpdateBinding

private lateinit var binding: ActivityUpdateBinding
class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}