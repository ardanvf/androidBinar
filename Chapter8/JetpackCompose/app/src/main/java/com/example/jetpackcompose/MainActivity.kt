package com.example.jetpackcompose

import android.icu.number.SimpleNotation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            
        }
    }
}

@Composable
fun MessageCard(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun Greeting(named: String){
    Text("Meksiko")
}