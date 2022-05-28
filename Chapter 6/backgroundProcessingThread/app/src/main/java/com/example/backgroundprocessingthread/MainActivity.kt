package com.example.backgroundprocessingthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import com.example.backgroundprocessingthread.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}

//val handler = object : Handler(Looper.getMainLooper()) {
//    override fun handleMessage(msg: Message) {
//        val message = msg.obj as String
//        binding.textView = message
//    }
//}
//
//fun onClick(v: View){
//    Thread(Runnable {
//        public override fun run(){
//            val text = loadText
//        }
//    }).start()
//}