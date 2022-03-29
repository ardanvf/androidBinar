package com.example.toastandsnackbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.toastandsnackbar.databinding.ActivityMainBinding
import com.example.toastandsnackbar.databinding.ActivitySnackbarsBinding

private lateinit var binding: ActivitySnackbarsBinding
class snackbars : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySnackbarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val first = first()

        binding.yesBtn.setOnClickListener{
//            val intents = Intent(this,yay::class.java)
//            startActivity(intents)
//            Toast.makeText(this, "Hello Human!!", Toast.LENGTH_LONG).show()
        }
        binding.noBtn.setOnClickListener{
            Toast.makeText(this, "Bip boppp bippp! Reset", Toast.LENGTH_LONG).show()
        }
    }
}