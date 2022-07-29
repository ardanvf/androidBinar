package com.example.challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challenge.Helper.Constant
import com.example.challenge.Helper.PreferencesHelper
import com.example.challenge.databinding.ActivityRegisterBinding

private lateinit var binding: ActivityRegisterBinding
class RegisterActivity : AppCompatActivity() {

//    private lateinit var mUserViewModel: UserViewModel
    lateinit var sharedpref: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedpref = PreferencesHelper(this)
        supportActionBar?.hide()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
//        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.button.setOnClickListener{
//            insertDataToDatabase()
            registerAkun()
        }

        binding.textLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        setContentView(binding.root)
    }

    private fun registerAkun(){
        if(binding.inpUsername.text.isNullOrEmpty() && binding.inpEmail.text.isNullOrEmpty()){
            Toast.makeText(this, "Isi Data Dengan Benar", Toast.LENGTH_SHORT).show()
        } else if (binding.inpPassword.text.toString() != binding.inpRePassword.text.toString()){
            Toast.makeText(this, "Masukkan Pasword dengan Benar", Toast.LENGTH_SHORT).show()
        } else{
            sharedpref.put(Constant.PREF_USERNAME, binding.inpUsername.text.toString())
            sharedpref.put(Constant.PREF_EMAIL, binding.inpEmail.text.toString())
            sharedpref.put(Constant.PREF_PASSWORD, binding.inpPassword.text.toString())
            sharedpref.put(Constant.PREF_ADDRESS,"")
            sharedpref.put(Constant.PREF_DATE,"")
            sharedpref.put(Constant.PREF_FULLNAME,"")
            sharedpref.putBool(Constant.PREF_IS_LOGIN, false)
            Toast.makeText(this, "Berhasil Daftar Akun", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}