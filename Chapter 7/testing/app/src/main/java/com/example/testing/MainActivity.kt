package com.example.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.example.testing.databinding.ActivityMainBinding


private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {

    val TAG = "Cekk"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var inputName = binding.editTextTextPersonName.text
        var inputPassword = binding.editTextTextPassword.text

        binding.button.setOnClickListener {
            testValidate(inputName, inputPassword)
        }

    }

    private val existingUsers = listOf("Ardan", "Venora")

    fun testValidate(username: Editable, password: Editable) {
        if (username.isNotEmpty()) {
            if (password.length >= 8) {
                Log.d(TAG, "Cek Berhasil $password $username")
            } else if(password.toString().capitalize() != password.toString()){
                Log.d(TAG, "Password harus ada huruf besar $password $username")
            } else {
                Log.d(TAG, "Cek Gagal, $password $username")
            }
        }
    }
}

