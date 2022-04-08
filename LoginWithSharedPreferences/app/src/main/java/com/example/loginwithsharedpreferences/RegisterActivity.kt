package com.example.loginwithsharedpreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.loginwithsharedpreferences.helper.Constant
import com.example.loginwithsharedpreferences.helper.PreferencesHelper

class RegisterActivity : AppCompatActivity() {

    lateinit var sharedpref: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedpref = PreferencesHelper(this)

    val btnRegister = findViewById<Button>(R.id.btnRegister)
    val registTextName = findViewById<EditText>(R.id.textRegistName)
    val registTextUsername = findViewById<EditText>(R.id.textRegistUsername)
    val registTextPassword = findViewById<EditText>(R.id.textRegistPassword)

    btnRegister.setOnClickListener{
        if (registTextName.text.isNotEmpty() && registTextUsername.text.isNotEmpty() && registTextPassword.text.isNotEmpty()){
            saveSession(registTextName.text.toString(), registTextName.text.toString(), registTextPassword.text.toString(), pref = false )
            showMessage("Anda Berhasil Daftar")
            moveIntent()
            } else {
                showMessage("Gagal Daftar")
            }
        }
    }

    private fun saveSession(name: String,username: String, password: String, pref: Boolean ){
        sharedpref.put( Constant.PREF_IS_NAME, name)
        sharedpref.put( Constant.PREF_IS_USERNAME, username)
        sharedpref.put( Constant.PREF_IS_PASSWORD, password)
        sharedpref.putBoolean( Constant.PREF_IS_LOGIN, pref)
    }

    private fun moveIntent(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showMessage(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}