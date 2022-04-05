package com.example.splash_login_homepage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.splash_login_homepage.databinding.ActivityLoginBinding
import java.util.prefs.AbstractPreferences

private lateinit var binding: ActivityLoginBinding

class login : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var remember = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("SHARED", Context.MODE_PRIVATE)

        remember = sharedPreferences.getBoolean("CHECK", false)

        if(remember){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val inptName = binding.editTextTextPersonName.text.toString()
        val inptPassword = binding.editTextTextPassword.text.toString()



        binding.btnLogin.setOnClickListener{
            val name: String = binding.editTextTextPersonName.text.toString()
            val password: String = binding.editTextTextPassword.text.toString()
            val checked: Boolean = binding.checker.isChecked

            if(name.isNullOrEmpty()){
                Toast.makeText(this, "Masukkan Nama", Toast.LENGTH_SHORT).show()
            } else if (password.isNullOrEmpty()){
                Toast.makeText(this, "Masukkan Password", Toast.LENGTH_LONG).show()
            } else{
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("NAME", name)
                editor.putString("PASSWORD", password)
                editor.putBoolean("CHECKBOX", checked)
                editor.apply()

                Toast.makeText(this, "Data Anda disimpan", Toast.LENGTH_SHORT). show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}