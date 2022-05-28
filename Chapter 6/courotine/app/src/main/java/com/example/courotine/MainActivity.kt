package com.example.courotine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

val TAG = "MeinActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Default).launch{
            //membuat coroutines tertidur, TIDAK dikeseluruhan thread
            delay(5000)
            Log.d(TAG, "Hello cek thread: ${Thread.currentThread().name}")
        }
        Log.d(TAG, "Hello dari thread: ${Thread.currentThread().name}")
        main()
    }

    suspend fun getDataFromServer(thread: Thread): String{
        delay(2000)
        return "Hello data dari server"
    }

    fun main(){
        GlobalScope.launch {
            delay(1000)
            Log.d(TAG, "Coroutines!!")
        }
        Log.d(TAG,"Helloo")
        Thread.sleep(5000)
    }
}