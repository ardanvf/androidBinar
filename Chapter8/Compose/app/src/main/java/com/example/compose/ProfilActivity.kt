package com.example.compose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme

class UpdateProfilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,) {
                gambar()
                CardWithContent()
            }

        }
    }
}

@Composable
fun CardWithContent() {
    val paddingModifier = Modifier.padding(4.dp)
    Card(
        elevation = 10.dp,
        contentColor = Color(0xFF37007A),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Nama :",
                modifier = paddingModifier,
                fontSize = 16.sp)
            Text(text = "Ardan",
                color = Color.Black,
                modifier = Modifier.padding(start = 4.dp),
            fontSize = 12.sp)
            Text(text = "Email :",
                modifier = paddingModifier,
                fontSize = 16.sp)
            Text(text = "ardanspaga@gmail.com",
                color = Color.Black,
                modifier = Modifier.padding(start = 4.dp),
                fontSize = 12.sp)
            EditButton()
            LogoutButton()
        }
    }
}

@Composable
fun EditButton(){
    val mContext = LocalContext.current

    Button(onClick = {
        mContext.startActivity(Intent(mContext, UpdtProfilActivity::class.java))
    },
        Modifier
            .padding(top = 10.dp)
            .width(280.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF6EA034)
        )) {
        Text(text = "Ubah Profil", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
    }
}

@Composable
fun LogoutButton(){
    val mContext = LocalContext.current

    Button(onClick = {
        mContext.startActivity(Intent(mContext, MainActivity::class.java))
    },
        Modifier
            .padding(top = 10.dp)
            .width(280.dp), colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFB90E0E)
        )) {
        Text(text = "Keluar dari Akun", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
    }
}