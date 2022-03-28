package com.example.toastandsnackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.toastandsnackbar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            if (binding.editText    .text.isNullOrEmpty()){
                Toast.makeText(this, "Masukkan Nama Anda", Toast.LENGTH_LONG).show()
            } else {
                val snack = Snackbar.make(it, "Contoh Snekbar", Snackbar.LENGTH_INDEFINITE)
                snack.setAction("Tombol1") {
                    Toast.makeText(this, "Toast dari Action Snackbar", Toast.LENGTH_LONG).show()
                }.show()
            }
        }

    }
}