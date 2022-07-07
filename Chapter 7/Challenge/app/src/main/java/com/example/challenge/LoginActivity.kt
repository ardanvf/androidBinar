package com.example.challenge

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challenge.Helper.Constant
import com.example.challenge.Helper.PreferencesHelper
import com.example.challenge.databinding.ActivityLoginBinding


private lateinit var binding: ActivityLoginBinding
class LoginActivity : AppCompatActivity() {


    lateinit var sharedpref: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedpref = PreferencesHelper(this)

        val email =sharedpref.getString(Constant.PREF_EMAIL)
        val password = sharedpref.getString(Constant.PREF_PASSWORD)

        binding.button.setOnClickListener{
            if(binding.inpEmail.text.isNullOrEmpty() && binding.inpPassword.text.isNullOrEmpty()){
                Toast.makeText(this, "Isi Kolom Terlebih Dahulu", Toast.LENGTH_SHORT).show()
            }else{
                if (binding.inpEmail.text.toString() == email && binding.inpPassword.text.toString() == password) {
                    sharedpref.putBool(Constant.PREF_IS_LOGIN, true)
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(this, "Email atau Password anda Salah", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        binding.textView2.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}