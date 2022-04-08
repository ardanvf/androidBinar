package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.room.helper.Constant
import com.example.room.helper.PreferencesHelper

class MainActivity : AppCompatActivity() {
//    lateinit var sharedpref: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment))
    }
//    override fun onStart() {
//        super.onStart()
//        if(sharedpref.getBoolean(Constant.PREF_IS_LOGIN) == false){
//            moveIntent()
//            finish()
//        }
//    }
//
//    private fun moveIntent(){
//        startActivity(Intent(this, LoginActivity::class.java))
//        finish()
//    }
}