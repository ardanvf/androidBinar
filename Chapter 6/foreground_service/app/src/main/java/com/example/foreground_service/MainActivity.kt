package com.example.foreground_service

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foreground_service.databinding.ActivityMainBinding
import com.example.foreground_service.foreground_service.ForegroundService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonStart1.setOnClickListener {
            ForegroundService.startService(this,"Foreground Service is running...")
        }
        binding.buttonStop1.setOnClickListener {
            ForegroundService.stopService(this)
        }
    }
}