package com.example.compose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme

class UpdtProfilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Column(modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Greeting("Perbarui Akun")
                    TextFieldUsername()
                    TextFieldEmail()
                    TextFieldPassword("Masukkan Password")
                    TextFieldPassword("Konfirmasi Password")
                    UpdateButton()
                }
            }
        }
    }
}

@Composable
fun UpdateButton(){
    val mContext = LocalContext.current

    Button(onClick = {
        Toast.makeText(mContext, "Berhasil Memperbarui", Toast.LENGTH_SHORT).show()
        mContext.startActivity(Intent(mContext, UpdateProfilActivity::class.java))
    },
        Modifier
            .padding(top = 10.dp)
            .width(280.dp)) {
        Text(text = "Perbarui", fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}