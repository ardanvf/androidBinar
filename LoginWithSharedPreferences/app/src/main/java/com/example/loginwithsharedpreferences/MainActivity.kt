package com.example.loginwithsharedpreferences

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.loginwithsharedpreferences.helper.Constant
import com.example.loginwithsharedpreferences.helper.PreferencesHelper
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var sharedpref: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedpref = PreferencesHelper(this)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnDaftar = findViewById<TextView>(R.id.textLogin)
        val text = findViewById<TextView>(R.id.textView2)
        val text2 = findViewById<TextView>(R.id.textView3)
        val text3 = findViewById<TextView>(R.id.textView4)

        val editTextUsername = findViewById<EditText>(R.id.editUsername)
        val editTextPassword = findViewById<EditText>(R.id.editPassword)

        val named = sharedpref.getString(Constant.PREF_IS_NAME)
        val username = sharedpref.getString(Constant.PREF_IS_USERNAME)
        val password = sharedpref.getString(Constant.PREF_IS_PASSWORD)

        text.text = named
        text2.text = username
        text3.text = password


        btnLogin.setOnClickListener{
            if (editTextUsername.text.toString() == username && editTextPassword.text.toString() == password){
                Session(true)
                moveIntent()
            } else{
                showMessage("Username atau Password anda Salah")
            }
        }
        btnDaftar.setOnClickListener{
            registIntent()
        }
    }

    override fun onStart() {
        super.onStart()
        if(sharedpref.getBoolean(Constant.PREF_IS_LOGIN) == true){
            moveIntent()
            finish()
        }
    }

    private fun registIntent(){
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

    private fun moveIntent(){
        startActivity(Intent(this, Home::class.java))
        finish()
    }

    private fun Session(pref: Boolean ){
        sharedpref.putBoolean( Constant.PREF_IS_LOGIN, pref)
    }

    private fun showMessage(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}