package com.example.challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challenge.Helper.Constant
import com.example.challenge.Helper.PreferencesHelper
import com.example.challenge.databinding.ActivityUpdateBinding

private lateinit var binding: ActivityUpdateBinding
class UpdateActivity : AppCompatActivity() {
    lateinit var sharedpref: PreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        sharedpref = PreferencesHelper(this)

        binding.inpUsername.setText(sharedpref.getString(Constant.PREF_USERNAME))
        binding.inpFullname.setText(sharedpref.getString(Constant.PREF_FULLNAME))
        binding.inpDate.setText(sharedpref.getString(Constant.PREF_DATE))
        binding.inpAddress.setText(sharedpref.getString(Constant.PREF_ADDRESS))

        binding.btnUpdate.setOnClickListener {
            updateAkun()
        }

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    override fun onStart(){
        super.onStart()
        if(sharedpref.getBool(Constant.PREF_IS_LOGIN) == false){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun updateAkun(){

        if(binding.inpUsername.text.isNullOrEmpty() && binding.inpFullname.text.isNullOrEmpty() && binding.inpDate.text.isNullOrEmpty() && binding.inpAddress.text.isNullOrEmpty()){
            Toast.makeText(this, "Isi Kolomnya Terlebih Dahulu", Toast.LENGTH_SHORT).show()
        } else {
            sharedpref.put(Constant.PREF_USERNAME, binding.inpUsername.text.toString())
            sharedpref.put(Constant.PREF_FULLNAME, binding.inpFullname.text.toString())
            sharedpref.put(Constant.PREF_DATE, binding.inpDate.text.toString())
            sharedpref.put(Constant.PREF_ADDRESS, binding.inpAddress.text.toString())
            Toast.makeText(this, "Berhasil Update Akun", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun logout(){
        sharedpref.clear()
        Toast.makeText(this, "Berhasil Logout", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}