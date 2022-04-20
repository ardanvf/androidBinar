package com.example.challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.challenge.Data.User
import com.example.challenge.Data.UserViewModel
import com.example.challenge.databinding.ActivityRegisterBinding

private lateinit var binding: ActivityRegisterBinding
class RegisterActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.button.setOnClickListener{
            insertDataToDatabase()
        }
        setContentView(binding.root)
    }

    private fun insertDataToDatabase() {
        val username = binding.registUsername.editText.toString()
        val email = binding.registEmail.editText.toString()
        val password = binding.registPassword.editText.toString()

        if(username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
            val user = User(0,username,email,"Kosong", password, "Kosong")
            mUserViewModel.addUser(user)
            Toast.makeText(this, "Berhasil Daftar Akun Anda", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            Toast.makeText(this, "Gagal Daftar Akun Anda", Toast.LENGTH_SHORT).show()
        }
    }
}