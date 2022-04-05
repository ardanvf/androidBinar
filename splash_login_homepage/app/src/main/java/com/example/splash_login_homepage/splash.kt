package com.example.splash_login_homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.splash_login_homepage.databinding.ActivityMainBinding



class splash : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this,login::class.java)
            startActivity(intent)
            finish()
        }, 2000)


    }
}