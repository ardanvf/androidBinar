package com.example.splash_login_homepage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.splash_login_homepage.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {

    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences("SHARED", Context.MODE_PRIVATE)

        val name = preferences.getString("NAME", "")
        binding.txtName.text = name

        val password = preferences.getString("PASSWORD", "")
        binding.txtPassword.text = password

        binding.btnLogout.setOnClickListener{
            val editor: SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }

    }
}